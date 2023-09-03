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

    public Double getRgrp(Integer ProjectId, Integer AlgorithmId, ArrayList<Integer>[] Groups) {

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


        // for para o grupo
        // for para cada pessoa do grupo

        List<List<Double>> rindvUserGroups = new ArrayList<>();
        ArrayList<Double> rgrpGroups = new ArrayList<>();
        double somaRindvUserId = 0.0;

        for(int i = 0; i < Groups.length; i++){
            rindvUserGroups.add(new ArrayList<>());
            for(int j = 0; j < Groups[i].size(); j++){
                int userId = Groups[i].get(j);
                for (Recommendation r : lista1) {
                    for (RecommendationRating irr : lista2) {
                        if (r.getEvaluator().getId() == irr.getEvaluator().getId() &&
                                irr.getRecommendation().getId() == r.getId() &&
                                r.getAlgorithm().getId() == AlgorithmId && userId == r.getEvaluator().getId()) {
                            auxli = auxli + Math.pow(r.getWeight() - irr.getScore(), 2);
                            xComparacao++;
                        }
                    }
                }
                double rindvUserId = auxli / xComparacao;
                somaRindvUserId = somaRindvUserId + rindvUserId;
                rindvUserGroups.get(i).add(rindvUserId); //Inserir a injustiça individual do usuário (userID) como elemento de um dos grupos (vetores)
            }
            rgrpGroups.add(somaRindvUserId/Groups[i].size()); //rgrpGroups[i].add(sum(lista)/len(lista));
            somaRindvUserId = 0.0;
            xComparacao = 0;
            auxli = 0;
        }

        // Calcular a variância considerando os valores armazenados em rgrpGroups

        for (Double lIs : rgrpGroups) {
            auxRgrp = auxRgrp + (Math.pow(lIs - mediaLI, 2));
        }
        auxRgrp = auxRgrp / rgrpGroups.size();

        return auxRgrp;
    }
}

