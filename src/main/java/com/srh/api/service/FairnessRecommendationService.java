package com.srh.api.service;


import com.srh.api.model.ItemRating;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import com.srh.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    public double[][] getFairnessRecomendation(Integer ProjectId, Integer algorithmId) {
        final int usuarios = (int) evaluatorRepository.count();
        final int linha = usuarios;
        final int coluna = (int) itemRepository.count();

        double[][] xoriginal = new double[linha][coluna];
        double[][] xestimada = new double[linha][coluna];
        double[][] xavaliacao = new double[linha][coluna];

        int[] celulasConhecidas = new int[usuarios];
        int[] celulasNaoConhecidas = new int[usuarios];
        double[] difMediaUsuarios = new double[usuarios];


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
                avaliacaoResultado = itemRatingRepository.findByEvaluatorAndItem(l+1, c+1);
                if (avaliacaoResultado.isPresent()) {
                    avaliacao = avaliacaoResultado.get();
                    xoriginal[l][c] = avaliacao.getScore();
                    celulasConhecidas[l]++;
                }else {
                    xoriginal[l][c] = 0;
                    celulasNaoConhecidas[l]++;
                }
            }
        }

        //GERAR RECOMENDACAO

        //MATRIZ ESTIMADA - Prediction
        Optional<Recommendation> recomendacaoResultado;
        Recommendation recomendacao;
        for(int l = 0; l < linha; l++) {
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0){
                    recomendacaoResultado = recommendationRepository.findByEvaluatorAndItem(l+1, c+1, algorithmId);
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

        //Gerar Xavalicao
        Optional<RecommendationRating> avaliacaoRecomendacaoResultado;
        RecommendationRating avaliacaoRecomendacao;
        for(int l = 0; l < linha; l++) {
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0){
                    recomendacaoResultado = recommendationRepository.findByEvaluatorAndItem(l+1, c+1, algorithmId);

                    if(recomendacaoResultado.isPresent()){
                        recomendacao = recomendacaoResultado.get();
                        int recomendacaoId = recomendacao.getId();
                        avaliacaoRecomendacaoResultado = recommendationRatingRepository.findByRecommendationId(recomendacaoId);

                        if(avaliacaoRecomendacaoResultado.isPresent()){
                            avaliacaoRecomendacao = avaliacaoRecomendacaoResultado.get();
                            xavaliacao[l][c] = avaliacaoRecomendacao.getScore();
                        }
                    }
                }else{
                    xavaliacao[l][c] = xoriginal[l][c];
                }
            }
        }

        double auxLi = 0;
        double auxMedia = 0;
        int qtdRepeticao = 0;
        //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
        for(int l = 0; l < linha; l++){
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0) {
                    auxLi = auxLi + (Math.pow(xestimada[l][c] - xavaliacao[l][c], 2) / 4);
                    auxMedia = xestimada[l][c] - xavaliacao[l][c];
                    qtdRepeticao++;
                }
            }
            li[l] = auxLi / qtdRepeticao;
            difMediaUsuarios[l] = auxMedia / qtdRepeticao;
            auxMedia = 0;
            auxLi = 0;
            qtdRepeticao = 0;
        }

//        int u = 0;
//        while (u < coluna) {
//            //COMPRANDO OS ERROS DA MATRIZE ESTIMADAS - A MATRIZ ORIGINAL
//            for (int lo = 0; lo < xoriginal.length; lo++) {
//                //int u = 0;
//                if (xoriginal[lo][u] == 0) {
//                    calculandoli[u] = calculandoli[u] + (Math.pow(xavaliacao[lo][u] - xestimada[lo][u], 2));
//                }
//            }
//            u++;
//        }

//        //ENCONTRANDO O li DA JUSTIÇA INDIVIDUAL DE CADA USUÁRIO
//        for (int i = 0; i < usuarios; i++) {
//            li[i] = calculandoli[i] / celulasConhecidas[i];
//            //System.out.println("LI[" + i + "]: " + li[i]);
//        }

        double[][] x1 = new double[linha][coluna];
        for(int l = 0; l < linha; l++){
            for(int c = 0; c < coluna; c++){
                if (xoriginal[l][c] == 0){
                    Random random = new Random();

                    double numeroAleatorio = random.nextDouble();
                    double numeroFinal = 0;

                    // Calcule o número aleatório ajustado para o intervalo desejado
                    if(difMediaUsuarios[l] >= 0){
                        numeroFinal = xestimada[l][c] + (numeroAleatorio * li[l]);
                    }else {
                        numeroFinal = xestimada[l][c] - (numeroAleatorio * li[l]);
                    }
                    x1[l][c] = numeroFinal;
                }else {
                    x1[l][c] = xoriginal[l][c];
                }
            }
        }

        double lix[] = new double[usuarios];
        auxLi = 0;
        auxMedia = 0;
        qtdRepeticao = 0;
        //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
        for(int l = 0; l < linha; l++){
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0) {
                    auxLi = auxLi + (Math.pow(x1[l][c] - xavaliacao[l][c], 2) / 4);
                    auxMedia = x1[l][c] - xavaliacao[l][c];
                    qtdRepeticao++;
                }
            }
            lix[l] = auxLi / qtdRepeticao;
            difMediaUsuarios[l] = auxMedia / qtdRepeticao;
            auxMedia = 0;
            auxLi = 0;
            qtdRepeticao = 0;
        }

        System.out.println("Antes: " + Arrays.toString(li));
        System.out.println("Dps: " + Arrays.toString(lix));

        return x1;
    }
}
