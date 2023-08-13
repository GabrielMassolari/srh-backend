package com.srh.api.service;

import com.srh.api.model.*;
import com.srh.api.repository.ItemRatingRepository;
import com.srh.api.repository.ItemRepository;
import com.srh.api.repository.ProjectRepository;
import com.srh.api.repository.RecommendationRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RpolService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Double getRpol(Integer ProjectId, Integer AlgorithmId) {

        ArrayList<Double> rpol = new ArrayList<>();
        double auxRpol = 0;

        ArrayList<Double> varItem = new ArrayList<>();
        double auxVarItem = 0;

        double totalVar = 0;
        int qtdAvaliacao = 0;
        double totalItem = 0;
        double mediaItem = 0;

        ArrayList<Double> listaAlgoritmo1 = new ArrayList<>();
        ArrayList<Double> listaAlgoritmo2 = new ArrayList<>();
        boolean a1 = false;
        boolean a2 = false;
        boolean a4 = false;

        ArrayList<Double> sublista = null;

        Iterable<ItemRating> lista1 =
                itemRatingRepository.findAll();
        Iterable<Recommendation> lista2 =
                recommendationRepository.findAll();
        Iterable<Item> itens = itemRepository.findAll();

        if (AlgorithmId == 4) {
            for (Item i : itens) {
                sublista = new ArrayList();
                for (ItemRating ir : lista1) {
                    if (ir.getId().getItem().getId() == i.getId()) {
                        sublista.add(ir.getScore());
                    }
                }
                for (Recommendation r : lista2) {
                    if (r.getAlgorithm().getId() == 1 &&
                            r.getItem().getId() == i.getId()) {
                        listaAlgoritmo1.add(r.getWeight());
                        a1 = true;
                    }
                    if (r.getAlgorithm().getId() == 2 &&
                            r.getItem().getId() == i.getId()) {
                        listaAlgoritmo2.add(r.getWeight());
                        a2 = true;
                    }
                    if (a1 == true && a2 == true) {
                        a4 = true;
                    }
                }
                double menor = 0;
                for (int x = 0; x < listaAlgoritmo1.size(); x++) {
                    if (a4 == true) {
                        if (listaAlgoritmo1.get(x).doubleValue() <
                                listaAlgoritmo2.get(x).doubleValue()) {
                            menor = listaAlgoritmo1.get(x);
                            sublista.add(menor);
                        } else {
                            menor = listaAlgoritmo2.get(x);
                            sublista.add(menor);
                        }
                    }
                }
                if (sublista.size() != i.getProject().getEvaluators().size()) {
                    sublista.clear();
                    listaAlgoritmo1.clear();
                    listaAlgoritmo2.clear();
                    a4 = false;
                }
                for (Double d : sublista) {
                    totalItem = totalItem + d;
                    qtdAvaliacao++;
                }
                mediaItem = totalItem / qtdAvaliacao;

                for (Double sub : sublista) {
                    auxVarItem = auxVarItem + (Math.pow(sub - mediaItem, 2));
                }
                auxVarItem = auxVarItem / qtdAvaliacao;

                varItem.add(auxVarItem);

                totalVar = totalVar + auxVarItem;

                auxRpol = totalVar / varItem.size();

                totalItem = 0;
                qtdAvaliacao = 0;
                mediaItem = 0;
                auxVarItem = 0;
                listaAlgoritmo1.clear();
                listaAlgoritmo2.clear();
                a1 = false;
                a2 = false;
                a4 = false;
            }

        } else {
            for (Item i : itens) {

                sublista = new ArrayList();

                for (ItemRating ir : lista1) {
                    if (ir.getId().getItem().getId() == i.getId()) {
                        sublista.add(ir.getScore());
                    }
                }
                for (Recommendation r : lista2) {
                    if (r.getItem().getId() == i.getId() &&
                            r.getAlgorithm().getId() == AlgorithmId) {
                        sublista.add(r.getWeight());
                    }
                }

                if (sublista.size() != i.getProject().getEvaluators().size()) {
                    sublista.clear();
                }

                for (Double d : sublista) {
                    totalItem = totalItem + d;
                    qtdAvaliacao++;
                }

                mediaItem = totalItem / qtdAvaliacao;

                for (Double sub : sublista) {
                    auxVarItem = auxVarItem + (Math.pow(sub - mediaItem, 2));
                }
                auxVarItem = auxVarItem / qtdAvaliacao;

                varItem.add(auxVarItem);

                totalVar = totalVar + auxVarItem;

                auxRpol = totalVar / varItem.size();

                totalItem = 0;
                qtdAvaliacao = 0;
                mediaItem = 0;
                auxVarItem = 0;
            }
        }
        rpol.add(auxRpol);
        sublista.clear();
        listaAlgoritmo1.clear();
        listaAlgoritmo2.clear();
        a1 = false;
        a2 = false;
        a4 = false;
        varItem.clear();
        totalItem = 0;
        totalVar = 0;
        qtdAvaliacao = 0;
        mediaItem = 0;
        auxVarItem = 0;

        return auxRpol;
    }
}