package com.srh.api.service;

import com.srh.api.algorithms.resources.AlgorithmStrategy;
import com.srh.api.algorithms.resources.AlgorithmStrategyAsync;
import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.*;
import com.srh.api.repository.ItemRepository;
import com.srh.api.repository.RecommendationRepository;
import com.srh.api.utils.PageUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private AlgorithmStrategy algorithmStrategy;

    @Autowired
    private AlgorithmStrategyAsync algorithmStrategyAsync;

    public Recommendation find(Integer id) {
        Optional<Recommendation> recommendation = recommendationRepository.findById(id);

        if (recommendation.isPresent())
            return recommendation.get();

        throw new ObjectNotFoundException(id, Recommendation.class.getName());
    }

    public Page<Recommendation> findAll(Pageable pageInfo) {
        return recommendationRepository.findAll(pageInfo);
    }

    public List<RecommendationsByEvaluator> generateRecommendations(RecommendationForm form) {
        RecommendationAlgorithm algorithm = algorithmStrategy.getAlgorithm(form.getAlgorithmId());
        return algorithm.calc(form);
    }

    @Async
    public CompletableFuture<List<RecommendationsByEvaluator>> generateAsyncRecommendations(RecommendationForm form) {
        RecommendationAlgorithm algorithm = algorithmStrategyAsync.getAlgorithm(form.getAlgorithmId());
        List<RecommendationsByEvaluator> results = algorithm.calc(form);
        return CompletableFuture.completedFuture(results);
    }

    public List<RecommendationsByEvaluator> generateOfflineRecommendations(RecommendationForm form) {
        List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
        List<Evaluator> evaluators = evaluatorService.findAll(Pageable.unpaged()).getContent();

        for (Evaluator evaluator : evaluators) {
            RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
            recommendationsByEvaluator.setEvaluator(evaluator);
            List<Recommendation> recommendations = recommendationRepository
                    .listLastRecommendationsByAlgorithmId(form.getAlgorithmId(), form.getProjectId(), evaluator.getId());
            recommendationsByEvaluator.setRecommendations(recommendations);
            recommendationsByEvaluators.add(recommendationsByEvaluator);
        }
        return recommendationsByEvaluators;
    }

    public List<Recommendation> saveList(List<Recommendation> recommendations) {
        return (List<Recommendation>) recommendationRepository.saveAll(recommendations);
    }

    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public Recommendation update(Recommendation recommendation) {
        find(recommendation.getId());
        return recommendationRepository.save(recommendation);
    }

    public void delete(Integer id) {
        find(id);
        recommendationRepository.deleteById(id);
    }

    public Page<Recommendation> listRecommendationsByAlgorithm(Integer algorithmId, Pageable pageInfo) {
        Algorithm algorithm = algorithmService.find(algorithmId);
        List<Recommendation> recommendations = recommendationRepository.findByAlgorithm(algorithm);
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }

    public Page<Recommendation> listRecommendationsByMatrixId(Integer matrixId, Pageable pageInfo) {
        List<Recommendation> recommendations = recommendationRepository.findByMatrixId(matrixId);
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }

    public Page<Recommendation> listRecommendationsByTag(Integer tagId, Pageable pageInfo) {
        Tag tag = tagService.find(tagId);
        List<Item> itens = itemRepository.findByTags(tag);
        List<Recommendation> recommendations = new ArrayList<>();

        for (Item item : itens) {
            recommendations.addAll(item.getRecommendations());
        }

        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }
}
