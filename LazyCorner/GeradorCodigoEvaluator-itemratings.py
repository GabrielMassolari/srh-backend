import random




RAWDATA = [[4,5,1,5,4,5,5,5,1,5,1],[5,4,5,5,4,3,4,4,2,4,2],[6,4,5,2,2,1,2,2,5,1,3],[7,5,3,3,4,2,4,1,5,2,4],[8,4,5,5,3,3,4,4,5,5,4],[9,5,2,3,5,5,3,4,3,3,0],[10,3,2,4,5,4,4,5,4,4,5],[11,4,2,2,3,2,5,3,3,4,2],[12,4,3,4,5,5,5,5,4,5,4],[13,5,5,5,2,1,3,5,5,5,3],[14,4,4,3,4,3,3,3,5,3,4],[15,5,1,1,3,3,3,4,2,5,5],[16,3,3,5,5,5,3,4,2,3,2],[17,4,5,4,5,3,3,3,5,5,5],[18,2,5,4,5,4,4,3,3,4,5],[19,5,5,2,5,1,4,5,5,3,1],[20,3,3,2,3,1,1,3,2,2,2],[21,5,1,1,5,4,5,5,4,1,1],[22,5,3,4,5,3,2,4,1,3,4],[23,5,3,5,3,3,5,4,4,4,2],[25,5,4,3,5,3,3,3,5,3,4],[26,4,3,3,2,1,4,5,2,4,1],[27,3,1,2,4,1,5,4,1,5,1],[28,5,5,5,1,1,1,5,5,1,5],[31,4,5,5,4,3,4,3,5,3,5],[32,1,5,1,5,5,3,1,5,2,5],[33,5,5,5,5,5,5,5,5,5,5],[34,5,4,5,3,4,3,1,5,3,3],[35,5,5,4,5,3,4,5,3,4,1],[36,5,4,5,5,5,5,2,2,1,3],[37,5,4,5,3,3,4,5,5,5,1],[38,4,5,3,4,4,1,4,4,1,4],[39,5,5,5,5,5,5,5,5,5,5],[40,5,5,2,5,5,5,5,3,4,1],[41,3,3,2,3,4,5,2,4,3,4],[42,4,3,2,1,2,5,5,5,5,5],[43,3,5,5,5,4,5,3,5,3,5],[44,1,1,1,5,5,5,5,1,1,5],[45,5,2,4,5,5,2,4,1,4,3],[46,2,5,1,5,5,5,1,5,5,1],[47,5,3,3,4,5,5,5,4,4,3],[48,5,5,5,5,5,4,4,4,5,5],[49,5,5,1,3,2,5,2,5,1,5],[50,3,4,5,5,4,5,5,5,5,5],[51,3,1,5,5,5,5,5,1,1,1],[52,3,3,3,3,3,3,4,5,3,5],[53,4,3,4,4,5,4,5,3,3,4],[54,3,3,2,3,4,4,5,4,4,5],[55,5,3,4,4,2,3,5,1,3,1],[56,1,4,5,4,5,4,5,4,2,3],[57,2,3,1,4,3,5,1,2,0,5],[58,5,5,4,1,1,3,5,5,4,1],[59,5,3,5,2,4,5,2,2,5,1],[60,2,5,3,4,2,3,4,5,2,5],[61,5,3,4,4,5,4,5,5,3,1],[62,5,4,4,5,5,4,5,5,4,2],[63,4,2,4,3,3,3,3,3,4,2],[64,3,4,3,4,3,4,4,4,4,4],[65,5,5,5,5,5,5,5,3,4,3]]



def generate():
    print("Gerando o necessário para o script\n\n")
    evaCount = 1 #começa pelo ID 1 ou crasha
    for eva in RAWDATA:
        print('Evaluator eva' + str(eva[0]) + ' = EvaluatorBuilder.anEvaluator().withId('+ str(evaCount) +').withName("usuario'+ str(eva[0]) +'").withEmail("user'+ str(eva[0]) +'@user.com").withLogin("user'+ str(eva[0]) +'").withPassword(BcriptyUtil.encripty("123456")).build();')
        evaCount += 1

    print("\n")
    # Resultados devem ser iguais sempre por conta da seed.
    random.seed(365)
    itemrating = 0
    for eva in RAWDATA:
        # pegar o primeiro, e dar um pop nele. 
        # pega o resto, retire 5 aleatórios.
        # salve-os como nota usando o negoco. pos no array = itemPOS
        # nao da pop nos aleatorios. use range(1,11) e de pop NELE.
        
        
        # decide quem vamos deixar. são 5 das 10 que devem ficar.
        restantes = [*range(1,11)]
        for i in range(5):
            restantes.pop(random.randint(1,len(restantes)-1 ))
        # Resultante contém 5 posições a serem consideradas, o resto será ignorado.
        
        for i in range(5):
            output = "ItemRating itemrating" + str(itemrating) + " = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(" + str(eva[restantes[i]]) +".0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva" + str(eva[0]) + ").withItem(item" + str(restantes[i]) + ").build()).build();"
            
            itemrating += 1
            
            print(output);
        
        #for c in r:
        #    print(c,end = " ")
        #print()

    print("List<Evaluator> listEvaluators = Arrays.asList(",end=" ")
    for eva in RAWDATA:
        print("eva" + str(eva[0]) + ", ",end= " ")
    print(");")
    
    print("List<Tag> listTags = Arrays.asList(",end=" ")
    for tag in range(23):
        print("tag" + str(tag+1) + ", ",end= " ")
    print(");")
    
    print("List<ItemRating> listItemRatings = Arrays.asList(",end=" ")
    for itemr in range(itemrating):
        print("itemrating" + str(itemr) + ", ", end=" ")
    print(");")
        


generate();


