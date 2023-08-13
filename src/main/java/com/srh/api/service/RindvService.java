package com.srh.api.service;

import com.srh.api.model.*;
import com.srh.api.repository.ProjectRepository;
import com.srh.api.repository.RecommendationRatingRepository;
import com.srh.api.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RindvService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    public Double getRindv(Integer ProjectId, Integer AlgorithmId) {

        ArrayList<Double> totaisMatrizes = new ArrayList<>();
        Double mediaLi;
        ArrayList<Double> liUser = new ArrayList<>();
        double auxli = 0;
        ArrayList<Double> rindv = new ArrayList<>();
        double auxRindv = 0;

        double totalItem = 0;
        double auxMediaLi = 0;
        int xComparacao = 0;
        int qtdScores = 0;

        Iterable<Recommendation> lista1 = recommendationRepository.findAll();
        Iterable<RecommendationRating> lista2 = recommendationRatingRepository.findAll();

        ArrayList<Double> listaAlgoritmo4 = new ArrayList<>();
        for (Recommendation r : lista1) {
            for (Recommendation r2 : lista1) {
                if (r.getAlgorithm().getId() == 1 &&
                        r2.getAlgorithm().getId() == 2 &&
                        r.getEvaluator().getId() == r2.getEvaluator().getId() &&
                        r.getItem().getId() == r2.getItem().getId()) {
                    if (r.getWeight().doubleValue() < r2.getWeight().doubleValue()) {
                        listaAlgoritmo4.add(r.getWeight());
                    } else {
                        listaAlgoritmo4.add(r2.getWeight());
                    }
                }
            }
        }

        if (AlgorithmId == 4) {
            for (int x = 0; x < listaAlgoritmo4.size(); x++) {
                for (RecommendationRating irr : lista2) {
                    if (irr.getId() == x + 1 &&
                            xComparacao < 2 &&
                            qtdScores < listaAlgoritmo4.size()) {
                        totalItem = totalItem + (listaAlgoritmo4.get(x) + irr.getScore());
                        auxli = auxli + Math.pow(listaAlgoritmo4.get(x) - irr.getScore(), 2);
                        xComparacao++;
                        qtdScores++;
                    }
                }
                if (xComparacao >= 2) {
                    auxli = auxli / xComparacao;
                    liUser.add(auxli);
                    auxli = 0;
                    totaisMatrizes.add(totalItem);
                    totalItem = 0;
                    xComparacao = 0;
                }
            }
        } else {
            for (Recommendation r : lista1) {
                for (RecommendationRating irr : lista2) {
                    if (r.getEvaluator().getId() == irr.getEvaluator().getId() &&
                            irr.getRecommendation().getId() == r.getId() &&
                            r.getAlgorithm().getId() == AlgorithmId) {
                        totalItem = totalItem + (r.getWeight() + irr.getScore());
                        auxli = auxli + Math.pow(r.getWeight() - irr.getScore(), 2);
                        xComparacao++;
                    }
                }
                if (xComparacao >= 2) {
                    auxli = auxli / xComparacao;
                    liUser.add(auxli);
                    auxli = 0;
                    totaisMatrizes.add(totalItem);
                    totalItem = 0;
                    xComparacao = 0;
                }
            }
        }

        for (int i = 0; i < liUser.size(); i++) {
            auxMediaLi = auxMediaLi + liUser.get(i);
        }
        mediaLi = auxMediaLi / liUser.size();

        for (Double lis : liUser) {
            auxRindv = auxRindv + (Math.pow(lis - mediaLi, 2));
        }
        auxRindv = auxRindv / liUser.size();

        rindv.add(auxRindv);

        mediaLi = 0.0;
        totalItem = 0.0;
        auxMediaLi = 0.0;
        liUser.clear();
        auxli = 0;
        qtdScores = 0;

        return auxRindv;
    }
}
