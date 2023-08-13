package com.srh.api.service;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;
import com.srh.api.model.Recommendation;
import com.srh.api.repository.EvaluatorRepository;
import com.srh.api.utils.PasswordUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluatorService {
    @Autowired
    private EvaluatorRepository evaluatorRepository;

    private PasswordUtil<Evaluator> passwordUtil = new PasswordUtil<>();

    public Evaluator find(Integer id) {
        Optional<Evaluator> evaluator = evaluatorRepository.findById(id);

        if (evaluator.isPresent())
            return evaluator.get();

        throw new ObjectNotFoundException(id, Evaluator.class.getName());
    }

    public Page<Evaluator> findAll(Pageable pageInfo) {
        return evaluatorRepository.findAll(pageInfo);
    }

    public Evaluator save(Evaluator evaluator) {
        Evaluator evaluatorEncoded = passwordUtil.encodedPasswordForUser(evaluator);
        return evaluatorRepository.save(evaluatorEncoded);
    }

    public Evaluator update(Evaluator evaluator, String oldRawPassword) {
        Evaluator oldEvaluator = find(evaluator.getId());
        Evaluator persistEvaluator = passwordUtil.verifyPasswordChanges(evaluator, oldEvaluator,
                oldRawPassword);

        persistEvaluator.setItemRatings(oldEvaluator.getItemRatings());
        persistEvaluator.setRecommendationRatings(oldEvaluator.getRecommendationRatings());
        persistEvaluator.setRecommendations(oldEvaluator.getRecommendations());

        return evaluatorRepository.save(persistEvaluator);
    }

    public void delete(Integer id) {
        find(id);
        evaluatorRepository.deleteById(id);
    }

    public Evaluator findByLogin(String login) {
        Optional<Evaluator> evaluator = evaluatorRepository.findByLogin(login);

        if (evaluator.isPresent())
            return evaluator.get();

        throw new ObjectNotFoundException(login, Evaluator.class.getName());
    }

    public List<Recommendation> listRecommendationsByEvaluator(Integer evaluatorId) {
        Evaluator evaluator = find(evaluatorId);
        return evaluator.getRecommendations();
    }
}
