package com.srh.api.service;


import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import com.srh.api.repository.ProjectRepository;
import com.srh.api.repository.RecommendationRatingRepository;
import com.srh.api.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RgrpService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    public Double getRgrp(Integer ProjectId, Integer AlgorithmId, ArrayList<Double>[] Groups) {

        //ArrayList<Double> grupo1;
        //ArrayList<Double> grupo2;
        //grupo1 = new ArrayList<>();
        //grupo2 = new ArrayList<>();

        ArrayList<Double> liUser = new ArrayList<>();
        double auxli = 0;
        ArrayList<Double> lIUser = new ArrayList<>();
        double auxLI = 0;
        ArrayList<Double> rgrp = new ArrayList<>();
        double auxRgrp = 0;

        double mediaLI = 0;
        double totalItem = 0;
        int xComparacao = 0;
        int grp = 0;
        int qtdScores = 0;

        Iterable<Recommendation> lista1 =
                recommendationRepository.findAll();
        Iterable<RecommendationRating> lista2 =
                recommendationRatingRepository.findAll();

        ArrayList<Double> listaAlgoritmo4 = new ArrayList<>();
        for (Recommendation r : lista1) {
            for (Recommendation r2 : lista1) {
                if (r.getAlgorithm().getId() == 1 &&
                        r2.getAlgorithm().getId() == 2 &&
                        r.getEvaluator().getId() == r2.getEvaluator().getId() &&
                        r.getItem().getId() == r2.getItem().getId()) {
                    if (r.getWeight().doubleValue()
                            < r2.getWeight().doubleValue()) {
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
                        totalItem = totalItem + (listaAlgoritmo4.get(x)
                                + irr.getScore());
                        auxli = auxli + Math.pow(listaAlgoritmo4.get(x)
                                - irr.getScore(), 2);
                        xComparacao++;
                        qtdScores++;
                    }
                }
                if (xComparacao >= 2) {
                    auxli = auxli / xComparacao;
                    liUser.add(auxli);
                    auxli = 0;
                    totalItem = 0;
                    xComparacao = 0;
                    qtdScores = 0;
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
            }
        }

        for(int i = 0; i < Groups.length; i++){
            for(int j = 0; j < Groups[i].size(); j++){
                auxLI = auxLI + Groups[i].get(j).doubleValue();
            }
            auxLI = auxLI / Groups[i].size();
            lIUser.add(auxLI);

            auxLI = 0;
        }

        for (int i = 0; i < lIUser.size(); i++) {
            auxLI = auxLI + lIUser.get(i);
        }
        mediaLI = auxLI / lIUser.size();

        for (Double lIs : lIUser) {
            auxRgrp = auxRgrp + (Math.pow(lIs - mediaLI, 2));
        }
        auxRgrp = auxRgrp / lIUser.size();

        rgrp.add(auxRgrp);

        liUser.clear();
        auxLI = 0.0;
        mediaLI = 0.0;
        lIUser.clear();
        qtdScores = 0;

        return auxRgrp;
    }
}

