package com.srh.api.service;


import com.srh.api.model.ItemRating;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import com.srh.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FairnessRecommendationService {
    @Autowired
    private EvaluatorRepository evaluatorRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    public class Rindv {
        public double[] getFairnessRecomendation(Integer ProjectId, Integer algorithmId) {
            final int usuarios = (int) evaluatorRepository.count();
            final int linha = usuarios;
            final int coluna = (int) itemRepository.count();

            double[][] xoriginal = new double[linha][coluna];
            double[][] xestimada = new double[linha][coluna];

            int[] celulasConhecidas = new int[usuarios];
            int[] celulasNaoConhecidas = new int[usuarios];


            double calculandoli[] = new double[usuarios];

            double li[] = new double[usuarios];
            double totali = 0;
            double mediali = 0;
            double rindv = 0;

            Iterable<ItemRating> avaliacoesItens =
                    itemRatingRepository.findAll();
            Iterable<Recommendation> recomendacoes =
                    recommendationRepository.findAll();

            //MATRIZ ORIGINAL
            Optional<ItemRating> avaliacaoResultado;
            ItemRating avaliacao;
            for(int l = 0; l < linha; l++) {
                for(int c = 0; c < coluna; c++){
                    avaliacaoResultado = itemRatingRepository.findByEvaluatorAndItem(l, c);
                    if (avaliacaoResultado.isPresent()) {
                        avaliacao = avaliacaoResultado.get();
                        xoriginal[l][c] = avaliacao.getScore();
                    }else {
                        xoriginal[l][c] = 0;
                    }
                }
            }

            //GERAR RECOMENDACAO

            //MATRIZ ESTIMADA - Prediction
            Optional<Recommendation> recomendacaoResultado;
            Recommendation recomendacao;
            for(int l = 0; l < linha; l++) {
                for(int c = 0; c < coluna; c++){
                    if(xoriginal[l][c] == -1){
                        recomendacaoResultado = recommendationRepository.findByEvaluatorAndItem(l, c, algorithmId);
                        if(recomendacaoResultado.isPresent()){
                            recomendacao = recomendacaoResultado.get();
                            xestimada[l][c] = recomendacao.getWeight();
                        }else{
                            //NAO TEM RECOMENDACAO - AVALIAR O QUE RETORNAR
                            //TALVEZ LANÇAR EXCECAO
                            xestimada[l][c] = 0;
                        }
                    }else{
                        xestimada[l][c] = xoriginal[l][c];
                    }
                }
            }

            // SOMANDO O TOTAL DAS CÉLULAS DE AVALIAÇÕES CONHECIDAS DA XORIGINAL VS XESTIMADA DE UM USUÁRIO
            int u = 0;
            while (u < coluna) {
                for (int lo = 0; lo < xoriginal.length; lo++) {
                    if (xoriginal[lo][u] != 0) {
                        celulasConhecidas[u]++;
                    } else {
                        celulasNaoConhecidas[u]++;
                    }
                }
                u++;
            }


            //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
            u = 0;
            while (u < coluna) {
                //COMPRANDO OS ERROS DA MATRIZE ESTIMADAS - A MATRIZ ORIGINAL
                for (int lo = 0; lo < xoriginal.length; lo++) {
                    //int u = 0;
                    if (xoriginal[lo][u] != 0) {
                        calculandoli[u] = calculandoli[u] + (Math.pow(xestimada[lo][u] - xoriginal[lo][u], 2));
                    }
                }
                u++;
            }

            //ENCONTRANDO O li DA JUSTIÇA INDIVIDUAL DE CADA USUÁRIO
            for (int i = 0; i < usuarios; i++) {
                li[i] = calculandoli[i] / celulasConhecidas[i];
            }


            return li;
        }
    }
}
