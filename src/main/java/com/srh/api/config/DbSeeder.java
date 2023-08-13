package com.srh.api.config;

import com.srh.api.builder.*;
import com.srh.api.model.*;
import com.srh.api.repository.*;
import com.srh.api.utils.BcriptyUtil;
import com.srh.api.utils.PasswordUtil;

import org.h2.engine.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DbSeeder {
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private EvaluatorRepository evaluatorRepository;


    @Autowired
    private ProjectRepository projectRepository;

    private Profile adminProfile;
    private Profile userProfile;

    public boolean seed() {
        createProfileAdmin();
        createProfileUser();

        createApiUserAdmin();
        createApiUserClient();

        createAlgorithms();

        //THE database.
        createMusicDatabase2();

        //TESTING
        //createTestProject();

        return true;
    }

    public void createMusicDatabase() {

        Admin admin = AdminBuilder.anAdmin()
            .withId(1)
            .withName("Mr. Admin")
            .withLogin("adm")
            .withEmail("admin@admin.com")
            .withPassword(BcriptyUtil.encripty("123456"))
            .build();
        adminRepository.save(admin);

        Project testProject = ProjectBuilder.aProject()
            .withId(1)
            .withDate(LocalDate.now())
            .withDescription("Test Project for Debugging")
            .withName("test")
            .withAdmin(admin)
            .withLastMatrixId(0)
            .build();

        Item item1  = ItemBuilder.anItem().withId(1).withName("Item1").withDescription("").withProject(testProject).build();
        Item item2  = ItemBuilder.anItem().withId(2).withName("Item2").withDescription("").withProject(testProject).build();
        Item item3  = ItemBuilder.anItem().withId(3).withName("Item3").withDescription("").withProject(testProject).build();
        Item item4  = ItemBuilder.anItem().withId(4).withName("Item4").withDescription("").withProject(testProject).build();
        Item item5  = ItemBuilder.anItem().withId(5).withName("Item5").withDescription("").withProject(testProject).build();
        Item item6  = ItemBuilder.anItem().withId(6).withName("Item6").withDescription("").withProject(testProject).build();
        Item item7  = ItemBuilder.anItem().withId(7).withName("Item7").withDescription("").withProject(testProject).build();
        Item item8  = ItemBuilder.anItem().withId(8).withName("Item8").withDescription("").withProject(testProject).build();
        Item item9  = ItemBuilder.anItem().withId(9).withName("Item9").withDescription("").withProject(testProject).build();
        Item item10 = ItemBuilder.anItem().withId(10).withName("Item10").withDescription("").withProject(testProject).build();
        
        //23 Tags
        Tag tag1 = TagBuilder.aTag().withId(1).withName("Década de 60")
            .withItens(Arrays.asList(item1))
            .build();
        Tag tag2 = TagBuilder.aTag().withId(2).withName("Década de 80")
            .withItens(Arrays.asList(item3,item7,item9))
            .build();
        Tag tag3 = TagBuilder.aTag().withId(3).withName("Década de 90")
            .withItens(Arrays.asList(item2))
            .build();
        Tag tag4 = TagBuilder.aTag().withId(4).withName("Década de 2000")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag5 = TagBuilder.aTag().withId(5).withName("Década de 2010")
            .withItens(Arrays.asList(item4,item5,item6))
            .build();
        Tag tag6 = TagBuilder.aTag().withId(6).withName("Década de 2020")
            .withItens(Arrays.asList(item10))
            .build();
        Tag tag7 = TagBuilder.aTag().withId(7).withName("Rock")
            .withItens(Arrays.asList(item1,item4,item5,item6,item7))
            .build();
        Tag tag8 = TagBuilder.aTag().withId(8).withName("Pop")
            .withItens(Arrays.asList(item1,item4,item5,item6))
            .build();
        Tag tag9 = TagBuilder.aTag().withId(9).withName("Britânico")
            .withItens(Arrays.asList(item1,item6))
            .build();
        Tag tag10 = TagBuilder.aTag().withId(10).withName("Samba")
            .withItens(Arrays.asList(item2))
            .build();
        Tag tag11 = TagBuilder.aTag().withId(11).withName("Brasileiro")
            .withItens(Arrays.asList(item2,item3,item8,item10))
            .build();
        Tag tag12 = TagBuilder.aTag().withId(12).withName("Latina")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag13 = TagBuilder.aTag().withId(13).withName("Funk")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag14 = TagBuilder.aTag().withId(14).withName("Soul")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag15 = TagBuilder.aTag().withId(15).withName("Canadense")
            .withItens(Arrays.asList(item4))
            .build();
        Tag tag16 = TagBuilder.aTag().withId(16).withName("Norte Americano")
            .withItens(Arrays.asList(item5))
            .build();
        Tag tag17 = TagBuilder.aTag().withId(17).withName("Australiano")
            .withItens(Arrays.asList(item7))
            .build();
        Tag tag18 = TagBuilder.aTag().withId(18).withName("Folk")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag19 = TagBuilder.aTag().withId(19).withName("Country")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag20 = TagBuilder.aTag().withId(20).withName("Reggae")
            .withItens(Arrays.asList(item9))
            .build();
        Tag tag21 = TagBuilder.aTag().withId(21).withName("Jamaicano")
            .withItens(Arrays.asList(item9))
            .build();
        Tag tag22 = TagBuilder.aTag().withId(22).withName("Forró")
            .withItens(Arrays.asList(item10))
            .build();
        Tag tag23 = TagBuilder.aTag().withId(23).withName("Tecnobrega")
            .withItens(Arrays.asList(item10))
            .build();
        /* Taaaags
        Década de 60
        Década de 80
        Década de 90
        Década de 2000
        Década de 2010
        Década de 2020
        Rock
        Pop
        Britânico
        Samba
        Brasileiro
        Latina
        Funk
        Soul
        Canadense
        Norte Americano
        Australiano
        Folk
        Country
        Reggae
        Jamaicano
        Forró
        Tecnobrega*/
        Evaluator eva4 = EvaluatorBuilder.anEvaluator().withId(1).withName("usuario4").withEmail("user4@user.com").withLogin("user4").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva5 = EvaluatorBuilder.anEvaluator().withId(2).withName("usuario5").withEmail("user5@user.com").withLogin("user5").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva6 = EvaluatorBuilder.anEvaluator().withId(3).withName("usuario6").withEmail("user6@user.com").withLogin("user6").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva7 = EvaluatorBuilder.anEvaluator().withId(4).withName("usuario7").withEmail("user7@user.com").withLogin("user7").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva8 = EvaluatorBuilder.anEvaluator().withId(5).withName("usuario8").withEmail("user8@user.com").withLogin("user8").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva9 = EvaluatorBuilder.anEvaluator().withId(6).withName("usuario9").withEmail("user9@user.com").withLogin("user9").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva10 = EvaluatorBuilder.anEvaluator().withId(7).withName("usuario10").withEmail("user10@user.com").withLogin("user10").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva11 = EvaluatorBuilder.anEvaluator().withId(8).withName("usuario11").withEmail("user11@user.com").withLogin("user11").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva12 = EvaluatorBuilder.anEvaluator().withId(9).withName("usuario12").withEmail("user12@user.com").withLogin("user12").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva13 = EvaluatorBuilder.anEvaluator().withId(10).withName("usuario13").withEmail("user13@user.com").withLogin("user13").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva14 = EvaluatorBuilder.anEvaluator().withId(11).withName("usuario14").withEmail("user14@user.com").withLogin("user14").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva15 = EvaluatorBuilder.anEvaluator().withId(12).withName("usuario15").withEmail("user15@user.com").withLogin("user15").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva16 = EvaluatorBuilder.anEvaluator().withId(13).withName("usuario16").withEmail("user16@user.com").withLogin("user16").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva17 = EvaluatorBuilder.anEvaluator().withId(14).withName("usuario17").withEmail("user17@user.com").withLogin("user17").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva18 = EvaluatorBuilder.anEvaluator().withId(15).withName("usuario18").withEmail("user18@user.com").withLogin("user18").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva19 = EvaluatorBuilder.anEvaluator().withId(16).withName("usuario19").withEmail("user19@user.com").withLogin("user19").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva20 = EvaluatorBuilder.anEvaluator().withId(17).withName("usuario20").withEmail("user20@user.com").withLogin("user20").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva21 = EvaluatorBuilder.anEvaluator().withId(18).withName("usuario21").withEmail("user21@user.com").withLogin("user21").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva22 = EvaluatorBuilder.anEvaluator().withId(19).withName("usuario22").withEmail("user22@user.com").withLogin("user22").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva23 = EvaluatorBuilder.anEvaluator().withId(20).withName("usuario23").withEmail("user23@user.com").withLogin("user23").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva25 = EvaluatorBuilder.anEvaluator().withId(21).withName("usuario25").withEmail("user25@user.com").withLogin("user25").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva26 = EvaluatorBuilder.anEvaluator().withId(22).withName("usuario26").withEmail("user26@user.com").withLogin("user26").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva27 = EvaluatorBuilder.anEvaluator().withId(23).withName("usuario27").withEmail("user27@user.com").withLogin("user27").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva28 = EvaluatorBuilder.anEvaluator().withId(24).withName("usuario28").withEmail("user28@user.com").withLogin("user28").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva31 = EvaluatorBuilder.anEvaluator().withId(25).withName("usuario31").withEmail("user31@user.com").withLogin("user31").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva32 = EvaluatorBuilder.anEvaluator().withId(26).withName("usuario32").withEmail("user32@user.com").withLogin("user32").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva33 = EvaluatorBuilder.anEvaluator().withId(27).withName("usuario33").withEmail("user33@user.com").withLogin("user33").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva34 = EvaluatorBuilder.anEvaluator().withId(28).withName("usuario34").withEmail("user34@user.com").withLogin("user34").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva35 = EvaluatorBuilder.anEvaluator().withId(29).withName("usuario35").withEmail("user35@user.com").withLogin("user35").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva36 = EvaluatorBuilder.anEvaluator().withId(30).withName("usuario36").withEmail("user36@user.com").withLogin("user36").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva37 = EvaluatorBuilder.anEvaluator().withId(31).withName("usuario37").withEmail("user37@user.com").withLogin("user37").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva38 = EvaluatorBuilder.anEvaluator().withId(32).withName("usuario38").withEmail("user38@user.com").withLogin("user38").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva39 = EvaluatorBuilder.anEvaluator().withId(33).withName("usuario39").withEmail("user39@user.com").withLogin("user39").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva40 = EvaluatorBuilder.anEvaluator().withId(34).withName("usuario40").withEmail("user40@user.com").withLogin("user40").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva41 = EvaluatorBuilder.anEvaluator().withId(35).withName("usuario41").withEmail("user41@user.com").withLogin("user41").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva42 = EvaluatorBuilder.anEvaluator().withId(36).withName("usuario42").withEmail("user42@user.com").withLogin("user42").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva43 = EvaluatorBuilder.anEvaluator().withId(37).withName("usuario43").withEmail("user43@user.com").withLogin("user43").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva44 = EvaluatorBuilder.anEvaluator().withId(38).withName("usuario44").withEmail("user44@user.com").withLogin("user44").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva45 = EvaluatorBuilder.anEvaluator().withId(39).withName("usuario45").withEmail("user45@user.com").withLogin("user45").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva46 = EvaluatorBuilder.anEvaluator().withId(40).withName("usuario46").withEmail("user46@user.com").withLogin("user46").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva47 = EvaluatorBuilder.anEvaluator().withId(41).withName("usuario47").withEmail("user47@user.com").withLogin("user47").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva48 = EvaluatorBuilder.anEvaluator().withId(42).withName("usuario48").withEmail("user48@user.com").withLogin("user48").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva49 = EvaluatorBuilder.anEvaluator().withId(43).withName("usuario49").withEmail("user49@user.com").withLogin("user49").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva50 = EvaluatorBuilder.anEvaluator().withId(44).withName("usuario50").withEmail("user50@user.com").withLogin("user50").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva51 = EvaluatorBuilder.anEvaluator().withId(45).withName("usuario51").withEmail("user51@user.com").withLogin("user51").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva52 = EvaluatorBuilder.anEvaluator().withId(46).withName("usuario52").withEmail("user52@user.com").withLogin("user52").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva53 = EvaluatorBuilder.anEvaluator().withId(47).withName("usuario53").withEmail("user53@user.com").withLogin("user53").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva54 = EvaluatorBuilder.anEvaluator().withId(48).withName("usuario54").withEmail("user54@user.com").withLogin("user54").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva55 = EvaluatorBuilder.anEvaluator().withId(49).withName("usuario55").withEmail("user55@user.com").withLogin("user55").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva56 = EvaluatorBuilder.anEvaluator().withId(50).withName("usuario56").withEmail("user56@user.com").withLogin("user56").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva57 = EvaluatorBuilder.anEvaluator().withId(51).withName("usuario57").withEmail("user57@user.com").withLogin("user57").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva58 = EvaluatorBuilder.anEvaluator().withId(52).withName("usuario58").withEmail("user58@user.com").withLogin("user58").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva59 = EvaluatorBuilder.anEvaluator().withId(53).withName("usuario59").withEmail("user59@user.com").withLogin("user59").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva60 = EvaluatorBuilder.anEvaluator().withId(54).withName("usuario60").withEmail("user60@user.com").withLogin("user60").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva61 = EvaluatorBuilder.anEvaluator().withId(55).withName("usuario61").withEmail("user61@user.com").withLogin("user61").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva62 = EvaluatorBuilder.anEvaluator().withId(56).withName("usuario62").withEmail("user62@user.com").withLogin("user62").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva63 = EvaluatorBuilder.anEvaluator().withId(57).withName("usuario63").withEmail("user63@user.com").withLogin("user63").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva64 = EvaluatorBuilder.anEvaluator().withId(58).withName("usuario64").withEmail("user64@user.com").withLogin("user64").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva65 = EvaluatorBuilder.anEvaluator().withId(59).withName("usuario65").withEmail("user65@user.com").withLogin("user65").withPassword(BcriptyUtil.encripty("123456")).build();

        ItemRating itemrating0 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item1).build()).build();
        ItemRating itemrating1 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item2).build()).build();
        ItemRating itemrating2 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item7).build()).build();
        ItemRating itemrating3 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item8).build()).build();
        ItemRating itemrating4 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item10).build()).build();
        ItemRating itemrating5 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item1).build()).build();
        ItemRating itemrating6 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item2).build()).build();
        ItemRating itemrating7 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item3).build()).build();
        ItemRating itemrating8 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item4).build()).build();
        ItemRating itemrating9 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item7).build()).build();
        ItemRating itemrating10 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item1).build()).build();
        ItemRating itemrating11 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item7).build()).build();
        ItemRating itemrating12 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item8).build()).build();
        ItemRating itemrating13 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item9).build()).build();
        ItemRating itemrating14 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item10).build()).build();
        ItemRating itemrating15 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item1).build()).build();
        ItemRating itemrating16 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item3).build()).build();
        ItemRating itemrating17 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item4).build()).build();
        ItemRating itemrating18 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item6).build()).build();
        ItemRating itemrating19 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item8).build()).build();
        ItemRating itemrating20 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item1).build()).build();
        ItemRating itemrating21 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item3).build()).build();
        ItemRating itemrating22 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item6).build()).build();
        ItemRating itemrating23 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item7).build()).build();
        ItemRating itemrating24 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item8).build()).build();
        ItemRating itemrating25 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item1).build()).build();
        ItemRating itemrating26 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item2).build()).build();
        ItemRating itemrating27 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item3).build()).build();
        ItemRating itemrating28 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item7).build()).build();
        ItemRating itemrating29 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item8).build()).build();
        ItemRating itemrating30 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item1).build()).build();
        ItemRating itemrating31 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item4).build()).build();
        ItemRating itemrating32 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item6).build()).build();
        ItemRating itemrating33 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item9).build()).build();
        ItemRating itemrating34 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item10).build()).build();
        ItemRating itemrating35 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item1).build()).build();
        ItemRating itemrating36 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item2).build()).build();
        ItemRating itemrating37 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item6).build()).build();
        ItemRating itemrating38 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item9).build()).build();
        ItemRating itemrating39 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item10).build()).build();
        ItemRating itemrating40 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item1).build()).build();
        ItemRating itemrating41 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item6).build()).build();
        ItemRating itemrating42 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item7).build()).build();
        ItemRating itemrating43 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item9).build()).build();
        ItemRating itemrating44 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item10).build()).build();
        ItemRating itemrating45 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item1).build()).build();
        ItemRating itemrating46 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item2).build()).build();
        ItemRating itemrating47 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item4).build()).build();
        ItemRating itemrating48 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item5).build()).build();
        ItemRating itemrating49 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item8).build()).build();
        ItemRating itemrating50 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item1).build()).build();
        ItemRating itemrating51 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item3).build()).build();
        ItemRating itemrating52 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item7).build()).build();
        ItemRating itemrating53 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item9).build()).build();
        ItemRating itemrating54 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item10).build()).build();
        ItemRating itemrating55 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item1).build()).build();
        ItemRating itemrating56 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item3).build()).build();
        ItemRating itemrating57 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item4).build()).build();
        ItemRating itemrating58 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item7).build()).build();
        ItemRating itemrating59 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item10).build()).build();
        ItemRating itemrating60 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item1).build()).build();
        ItemRating itemrating61 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item3).build()).build();
        ItemRating itemrating62 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item5).build()).build();
        ItemRating itemrating63 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item8).build()).build();
        ItemRating itemrating64 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item10).build()).build();
        ItemRating itemrating65 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item1).build()).build();
        ItemRating itemrating66 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item4).build()).build();
        ItemRating itemrating67 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item6).build()).build();
        ItemRating itemrating68 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item8).build()).build();
        ItemRating itemrating69 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item9).build()).build();
        ItemRating itemrating70 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item1).build()).build();
        ItemRating itemrating71 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item3).build()).build();
        ItemRating itemrating72 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item4).build()).build();
        ItemRating itemrating73 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item6).build()).build();
        ItemRating itemrating74 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item7).build()).build();
        ItemRating itemrating75 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item1).build()).build();
        ItemRating itemrating76 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item2).build()).build();
        ItemRating itemrating77 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item4).build()).build();
        ItemRating itemrating78 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item6).build()).build();
        ItemRating itemrating79 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item10).build()).build();
        ItemRating itemrating80 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item1).build()).build();
        ItemRating itemrating81 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item2).build()).build();
        ItemRating itemrating82 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item5).build()).build();
        ItemRating itemrating83 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item8).build()).build();
        ItemRating itemrating84 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item10).build()).build();
        ItemRating itemrating85 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item1).build()).build();
        ItemRating itemrating86 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item2).build()).build();
        ItemRating itemrating87 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item5).build()).build();
        ItemRating itemrating88 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item6).build()).build();
        ItemRating itemrating89 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item9).build()).build();
        ItemRating itemrating90 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item1).build()).build();
        ItemRating itemrating91 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item2).build()).build();
        ItemRating itemrating92 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item4).build()).build();
        ItemRating itemrating93 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item6).build()).build();
        ItemRating itemrating94 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item10).build()).build();
        ItemRating itemrating95 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item1).build()).build();
        ItemRating itemrating96 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item2).build()).build();
        ItemRating itemrating97 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item6).build()).build();
        ItemRating itemrating98 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item7).build()).build();
        ItemRating itemrating99 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item9).build()).build();
        ItemRating itemrating100 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item1).build()).build();
        ItemRating itemrating101 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item3).build()).build();
        ItemRating itemrating102 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item4).build()).build();
        ItemRating itemrating103 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item5).build()).build();
        ItemRating itemrating104 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item10).build()).build();
        ItemRating itemrating105 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item1).build()).build();
        ItemRating itemrating106 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item2).build()).build();
        ItemRating itemrating107 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item4).build()).build();
        ItemRating itemrating108 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item6).build()).build();
        ItemRating itemrating109 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item9).build()).build();
        ItemRating itemrating110 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item1).build()).build();
        ItemRating itemrating111 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item2).build()).build();
        ItemRating itemrating112 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item3).build()).build();
        ItemRating itemrating113 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item7).build()).build();
        ItemRating itemrating114 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item9).build()).build();
        ItemRating itemrating115 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item1).build()).build();
        ItemRating itemrating116 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item4).build()).build();
        ItemRating itemrating117 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item5).build()).build();
        ItemRating itemrating118 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item7).build()).build();
        ItemRating itemrating119 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item9).build()).build();
        ItemRating itemrating120 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item1).build()).build();
        ItemRating itemrating121 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item4).build()).build();
        ItemRating itemrating122 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item5).build()).build();
        ItemRating itemrating123 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item7).build()).build();
        ItemRating itemrating124 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item10).build()).build();
        ItemRating itemrating125 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item1).build()).build();
        ItemRating itemrating126 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item3).build()).build();
        ItemRating itemrating127 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item4).build()).build();
        ItemRating itemrating128 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item5).build()).build();
        ItemRating itemrating129 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item8).build()).build();
        ItemRating itemrating130 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item1).build()).build();
        ItemRating itemrating131 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item4).build()).build();
        ItemRating itemrating132 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item8).build()).build();
        ItemRating itemrating133 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item9).build()).build();
        ItemRating itemrating134 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item10).build()).build();
        ItemRating itemrating135 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item1).build()).build();
        ItemRating itemrating136 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item2).build()).build();
        ItemRating itemrating137 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item3).build()).build();
        ItemRating itemrating138 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item8).build()).build();
        ItemRating itemrating139 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item10).build()).build();
        ItemRating itemrating140 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item1).build()).build();
        ItemRating itemrating141 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item2).build()).build();
        ItemRating itemrating142 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item4).build()).build();
        ItemRating itemrating143 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item8).build()).build();
        ItemRating itemrating144 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item10).build()).build();
        ItemRating itemrating145 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item1).build()).build();
        ItemRating itemrating146 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item2).build()).build();
        ItemRating itemrating147 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item3).build()).build();
        ItemRating itemrating148 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item6).build()).build();
        ItemRating itemrating149 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item10).build()).build();
        ItemRating itemrating150 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item1).build()).build();
        ItemRating itemrating151 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item5).build()).build();
        ItemRating itemrating152 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item6).build()).build();
        ItemRating itemrating153 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item9).build()).build();
        ItemRating itemrating154 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item10).build()).build();
        ItemRating itemrating155 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item1).build()).build();
        ItemRating itemrating156 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item3).build()).build();
        ItemRating itemrating157 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item4).build()).build();
        ItemRating itemrating158 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item7).build()).build();
        ItemRating itemrating159 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item10).build()).build();
        ItemRating itemrating160 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item1).build()).build();
        ItemRating itemrating161 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item2).build()).build();
        ItemRating itemrating162 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item3).build()).build();
        ItemRating itemrating163 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item4).build()).build();
        ItemRating itemrating164 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item6).build()).build();
        ItemRating itemrating165 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item1).build()).build();
        ItemRating itemrating166 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item3).build()).build();
        ItemRating itemrating167 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item4).build()).build();
        ItemRating itemrating168 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item5).build()).build();
        ItemRating itemrating169 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item8).build()).build();
        ItemRating itemrating170 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item1).build()).build();
        ItemRating itemrating171 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item2).build()).build();
        ItemRating itemrating172 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item6).build()).build();
        ItemRating itemrating173 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item8).build()).build();
        ItemRating itemrating174 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item9).build()).build();
        ItemRating itemrating175 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item1).build()).build();
        ItemRating itemrating176 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item3).build()).build();
        ItemRating itemrating177 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item4).build()).build();
        ItemRating itemrating178 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item8).build()).build();
        ItemRating itemrating179 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item9).build()).build();
        ItemRating itemrating180 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item1).build()).build();
        ItemRating itemrating181 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item3).build()).build();
        ItemRating itemrating182 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item6).build()).build();
        ItemRating itemrating183 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item7).build()).build();
        ItemRating itemrating184 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item10).build()).build();
        ItemRating itemrating185 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item1).build()).build();
        ItemRating itemrating186 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item2).build()).build();
        ItemRating itemrating187 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item3).build()).build();
        ItemRating itemrating188 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item6).build()).build();
        ItemRating itemrating189 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item10).build()).build();
        ItemRating itemrating190 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item1).build()).build();
        ItemRating itemrating191 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item5).build()).build();
        ItemRating itemrating192 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item8).build()).build();
        ItemRating itemrating193 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item9).build()).build();
        ItemRating itemrating194 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item10).build()).build();
        ItemRating itemrating195 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item1).build()).build();
        ItemRating itemrating196 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item4).build()).build();
        ItemRating itemrating197 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item6).build()).build();
        ItemRating itemrating198 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item9).build()).build();
        ItemRating itemrating199 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item10).build()).build();
        ItemRating itemrating200 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item1).build()).build();
        ItemRating itemrating201 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item2).build()).build();
        ItemRating itemrating202 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item3).build()).build();
        ItemRating itemrating203 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item8).build()).build();
        ItemRating itemrating204 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item10).build()).build();
        ItemRating itemrating205 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item1).build()).build();
        ItemRating itemrating206 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item3).build()).build();
        ItemRating itemrating207 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item4).build()).build();
        ItemRating itemrating208 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item5).build()).build();
        ItemRating itemrating209 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item9).build()).build();
        ItemRating itemrating210 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item1).build()).build();
        ItemRating itemrating211 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item3).build()).build();
        ItemRating itemrating212 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item4).build()).build();
        ItemRating itemrating213 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item7).build()).build();
        ItemRating itemrating214 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item9).build()).build();
        ItemRating itemrating215 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item1).build()).build();
        ItemRating itemrating216 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item2).build()).build();
        ItemRating itemrating217 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item6).build()).build();
        ItemRating itemrating218 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item7).build()).build();
        ItemRating itemrating219 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item8).build()).build();
        ItemRating itemrating220 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item1).build()).build();
        ItemRating itemrating221 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item2).build()).build();
        ItemRating itemrating222 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item4).build()).build();
        ItemRating itemrating223 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item6).build()).build();
        ItemRating itemrating224 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item9).build()).build();
        ItemRating itemrating225 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item1).build()).build();
        ItemRating itemrating226 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item4).build()).build();
        ItemRating itemrating227 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item5).build()).build();
        ItemRating itemrating228 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item7).build()).build();
        ItemRating itemrating229 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item9).build()).build();
        ItemRating itemrating230 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item1).build()).build();
        ItemRating itemrating231 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item3).build()).build();
        ItemRating itemrating232 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item5).build()).build();
        ItemRating itemrating233 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item7).build()).build();
        ItemRating itemrating234 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item9).build()).build();
        ItemRating itemrating235 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item1).build()).build();
        ItemRating itemrating236 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item4).build()).build();
        ItemRating itemrating237 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item5).build()).build();
        ItemRating itemrating238 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item8).build()).build();
        ItemRating itemrating239 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item9).build()).build();
        ItemRating itemrating240 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item1).build()).build();
        ItemRating itemrating241 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item2).build()).build();
        ItemRating itemrating242 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item5).build()).build();
        ItemRating itemrating243 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item7).build()).build();
        ItemRating itemrating244 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item9).build()).build();
        ItemRating itemrating245 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item1).build()).build();
        ItemRating itemrating246 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item2).build()).build();
        ItemRating itemrating247 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item7).build()).build();
        ItemRating itemrating248 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item8).build()).build();
        ItemRating itemrating249 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item9).build()).build();
        ItemRating itemrating250 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item1).build()).build();
        ItemRating itemrating251 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item4).build()).build();
        ItemRating itemrating252 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item5).build()).build();
        ItemRating itemrating253 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item7).build()).build();
        ItemRating itemrating254 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item8).build()).build();
        ItemRating itemrating255 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item1).build()).build();
        ItemRating itemrating256 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item3).build()).build();
        ItemRating itemrating257 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item4).build()).build();
        ItemRating itemrating258 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item8).build()).build();
        ItemRating itemrating259 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item9).build()).build();
        ItemRating itemrating260 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item1).build()).build();
        ItemRating itemrating261 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item3).build()).build();
        ItemRating itemrating262 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item5).build()).build();
        ItemRating itemrating263 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item6).build()).build();
        ItemRating itemrating264 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item7).build()).build();
        ItemRating itemrating265 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item1).build()).build();
        ItemRating itemrating266 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item3).build()).build();
        ItemRating itemrating267 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item5).build()).build();
        ItemRating itemrating268 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item7).build()).build();
        ItemRating itemrating269 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item9).build()).build();
        ItemRating itemrating270 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item1).build()).build();
        ItemRating itemrating271 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item3).build()).build();
        ItemRating itemrating272 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item4).build()).build();
        ItemRating itemrating273 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item7).build()).build();
        ItemRating itemrating274 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item10).build()).build();
        ItemRating itemrating275 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item1).build()).build();
        ItemRating itemrating276 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item2).build()).build();
        ItemRating itemrating277 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item4).build()).build();
        ItemRating itemrating278 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item8).build()).build();
        ItemRating itemrating279 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item10).build()).build();
        ItemRating itemrating280 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item1).build()).build();
        ItemRating itemrating281 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item2).build()).build();
        ItemRating itemrating282 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item4).build()).build();
        ItemRating itemrating283 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item7).build()).build();
        ItemRating itemrating284 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item9).build()).build();
        ItemRating itemrating285 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item1).build()).build();
        ItemRating itemrating286 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item2).build()).build();
        ItemRating itemrating287 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item4).build()).build();
        ItemRating itemrating288 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item7).build()).build();
        ItemRating itemrating289 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item10).build()).build();
        ItemRating itemrating290 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item1).build()).build();
        ItemRating itemrating291 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item4).build()).build();
        ItemRating itemrating292 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item5).build()).build();
        ItemRating itemrating293 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item7).build()).build();
        ItemRating itemrating294 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item8).build()).build();
        
        
        projectRepository.save(testProject);

        List<Evaluator> listEvaluators = Arrays.asList( eva4,  eva5,  eva6,  eva7,  eva8,  eva9,  eva10,  eva11,  eva12,  eva13,  eva14,  eva15,  eva16,  eva17,  eva18,
            eva19,  eva20,  eva21,  eva22,  eva23,  eva25,  eva26,  eva27,  eva28,  eva31,  eva32,  eva33,  eva34,  eva35,  eva36,  eva37,  eva38,  eva39,  eva40,  eva41,
            eva42,  eva43,  eva44,  eva45,  eva46,  eva47,  eva48,  eva49,  eva50,  eva51,  eva52,  eva53,  eva54,  eva55,  eva56,  eva57,  eva58,  eva59,  eva60,  eva61,  eva62,  eva63,  eva64,  eva65);
        for(Evaluator ev : listEvaluators){
            ev.setProjects(Arrays.asList(testProject));
        }
        evaluatorRepository.saveAll(listEvaluators);

        List<Item> listItems = Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);
        itemRepository.saveAll(listItems);

        List<Tag> listTags = Arrays.asList( tag1,  tag2,  tag3,  tag4,  tag5,  tag6,  tag7,  tag8,  tag9,  tag10,  tag11,  tag12,  tag13,  tag14,  tag15,  tag16,  tag17,  tag18,  tag19,  tag20,  tag21,  tag22,  tag23  );
        tagRepository.saveAll(listTags);

        List<ItemRating> listItemRatings = Arrays.asList(
            itemrating0,  itemrating1,  itemrating2,  itemrating3,  itemrating4,  itemrating5,  itemrating6,  itemrating7,  itemrating8,  itemrating9,  itemrating10,  itemrating11,  itemrating12,  itemrating13,  itemrating14,  itemrating15,  itemrating16,  itemrating17,  itemrating18,  itemrating19,  itemrating20,
            itemrating21,  itemrating22,  itemrating23,  itemrating24,  itemrating25,  itemrating26,  itemrating27,  itemrating28,  itemrating29,  itemrating30,  itemrating31,  itemrating32,  itemrating33,  itemrating34,  itemrating35,  itemrating36,  itemrating37,  itemrating38,  itemrating39,  itemrating40,
            itemrating41,  itemrating42,  itemrating43,  itemrating44,  itemrating45,  itemrating46,  itemrating47,  itemrating48,  itemrating49,  itemrating50,  itemrating51,  itemrating52,  itemrating53,  itemrating54,  itemrating55,  itemrating56,  itemrating57,  itemrating58,  itemrating59,  itemrating60,  
            itemrating61,  itemrating62,  itemrating63,  itemrating64,  itemrating65,  itemrating66,  itemrating67,  itemrating68,  itemrating69,  itemrating70,  itemrating71,  itemrating72,  itemrating73,  itemrating74,  itemrating75,  itemrating76,  itemrating77,  itemrating78,  itemrating79,  itemrating80,  
            itemrating81,  itemrating82,  itemrating83,  itemrating84,  itemrating85,  itemrating86,  itemrating87,  itemrating88,  itemrating89,  itemrating90,  itemrating91,  itemrating92,  itemrating93,  itemrating94,  itemrating95,  itemrating96,  itemrating97,  itemrating98,  itemrating99,  itemrating100,  
            itemrating101,  itemrating102,  itemrating103,  itemrating104,  itemrating105,  itemrating106,  itemrating107,  itemrating108,  itemrating109,  itemrating110,  itemrating111,  itemrating112,  itemrating113,  itemrating114,  itemrating115,  itemrating116,  itemrating117,  itemrating118,  itemrating119,  itemrating120,
            itemrating121,  itemrating122,  itemrating123,  itemrating124,  itemrating125,  itemrating126,  itemrating127,  itemrating128,  itemrating129,  itemrating130,  itemrating131,  itemrating132,  itemrating133,  itemrating134,  itemrating135,  itemrating136,  itemrating137,  itemrating138,  itemrating139,  itemrating140,
            itemrating141,  itemrating142,  itemrating143,  itemrating144,  itemrating145,  itemrating146,  itemrating147,  itemrating148,  itemrating149,  itemrating150,  itemrating151,  itemrating152,  itemrating153,  itemrating154,  itemrating155,  itemrating156,  itemrating157,  itemrating158,  itemrating159,  itemrating160,
            itemrating161,  itemrating162,  itemrating163,  itemrating164,  itemrating165,  itemrating166,  itemrating167,  itemrating168,  itemrating169,  itemrating170,  itemrating171,  itemrating172,  itemrating173,  itemrating174,  itemrating175,  itemrating176,  itemrating177,  itemrating178,  itemrating179,  itemrating180,
            itemrating181,  itemrating182,  itemrating183,  itemrating184,  itemrating185,  itemrating186,  itemrating187,  itemrating188,  itemrating189,  itemrating190,  itemrating191,  itemrating192,  itemrating193,  itemrating194,  itemrating195,  itemrating196,  itemrating197,  itemrating198,  itemrating199,  itemrating200,
            itemrating201,  itemrating202,  itemrating203,  itemrating204,  itemrating205,  itemrating206,  itemrating207,  itemrating208,  itemrating209,  itemrating210,  itemrating211,  itemrating212,  itemrating213,  itemrating214,  itemrating215,  itemrating216,  itemrating217,  itemrating218,  itemrating219,  itemrating220,
            itemrating221,  itemrating222,  itemrating223,  itemrating224,  itemrating225,  itemrating226,  itemrating227,  itemrating228,  itemrating229,  itemrating230,  itemrating231,  itemrating232,  itemrating233,  itemrating234,  itemrating235,  itemrating236,  itemrating237,  itemrating238,  itemrating239,  itemrating240,  
            itemrating241,  itemrating242,  itemrating243,  itemrating244,  itemrating245,  itemrating246,  itemrating247,  itemrating248,  itemrating249,  itemrating250,  itemrating251,  itemrating252,  itemrating253,  itemrating254,  itemrating255,  itemrating256,  itemrating257,  itemrating258,  itemrating259,  itemrating260,
            itemrating261,  itemrating262,  itemrating263,  itemrating264, itemrating265,  itemrating266,  itemrating267,  itemrating268,  itemrating269,  itemrating270,  itemrating271,  itemrating272,  itemrating273,  itemrating274,  itemrating275,  itemrating276,  itemrating277,  itemrating278,  itemrating279,  itemrating280,
            itemrating281,  itemrating282,  itemrating283,  itemrating284,  itemrating285,  itemrating286,  itemrating287,  itemrating288,  itemrating289,  itemrating290,  itemrating291,  itemrating292,  itemrating293,  itemrating294
        );
        itemRatingRepository.saveAll(listItemRatings);
    }

    public void createMusicDatabase2(){
        Admin admin = AdminBuilder.anAdmin()
            .withId(1)
            .withName("Mr. Admin")
            .withLogin("adm")
            .withEmail("admin@admin.com")
            .withPassword(BcriptyUtil.encripty("123456"))
            .build();
        adminRepository.save(admin);

        Project testProject = ProjectBuilder.aProject()
            .withId(1)
            .withDate(LocalDate.now())
            .withDescription("Test Project for Debugging")
            .withName("test")
            .withAdmin(admin)
            .withLastMatrixId(0)
            .build();

        Item item1  = ItemBuilder.anItem().withId(1).withName("Item1").withDescription("").withProject(testProject).build();
        Item item2  = ItemBuilder.anItem().withId(2).withName("Item2").withDescription("").withProject(testProject).build();
        Item item3  = ItemBuilder.anItem().withId(3).withName("Item3").withDescription("").withProject(testProject).build();
        Item item4  = ItemBuilder.anItem().withId(4).withName("Item4").withDescription("").withProject(testProject).build();
        Item item5  = ItemBuilder.anItem().withId(5).withName("Item5").withDescription("").withProject(testProject).build();
        Item item6  = ItemBuilder.anItem().withId(6).withName("Item6").withDescription("").withProject(testProject).build();
        Item item7  = ItemBuilder.anItem().withId(7).withName("Item7").withDescription("").withProject(testProject).build();
        Item item8  = ItemBuilder.anItem().withId(8).withName("Item8").withDescription("").withProject(testProject).build();
        Item item9  = ItemBuilder.anItem().withId(9).withName("Item9").withDescription("").withProject(testProject).build();
        Item item10 = ItemBuilder.anItem().withId(10).withName("Item10").withDescription("").withProject(testProject).build();
        
        //23 Tags
        Tag tag1 = TagBuilder.aTag().withId(1).withName("Década de 60")
            .withItens(Arrays.asList(item1))
            .build();
        Tag tag2 = TagBuilder.aTag().withId(2).withName("Década de 80")
            .withItens(Arrays.asList(item3,item7,item9))
            .build();
        Tag tag3 = TagBuilder.aTag().withId(3).withName("Década de 90")
            .withItens(Arrays.asList(item2))
            .build();
        Tag tag4 = TagBuilder.aTag().withId(4).withName("Década de 2000")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag5 = TagBuilder.aTag().withId(5).withName("Década de 2010")
            .withItens(Arrays.asList(item4,item5,item6))
            .build();
        Tag tag6 = TagBuilder.aTag().withId(6).withName("Década de 2020")
            .withItens(Arrays.asList(item10))
            .build();
        Tag tag7 = TagBuilder.aTag().withId(7).withName("Rock")
            .withItens(Arrays.asList(item1,item4,item5,item6,item7))
            .build();
        Tag tag8 = TagBuilder.aTag().withId(8).withName("Pop")
            .withItens(Arrays.asList(item1,item4,item5,item6))
            .build();
        Tag tag9 = TagBuilder.aTag().withId(9).withName("Britânico")
            .withItens(Arrays.asList(item1,item6))
            .build();
        Tag tag10 = TagBuilder.aTag().withId(10).withName("Samba")
            .withItens(Arrays.asList(item2))
            .build();
        Tag tag11 = TagBuilder.aTag().withId(11).withName("Brasileiro")
            .withItens(Arrays.asList(item2,item3,item8,item10))
            .build();
        Tag tag12 = TagBuilder.aTag().withId(12).withName("Latina")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag13 = TagBuilder.aTag().withId(13).withName("Funk")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag14 = TagBuilder.aTag().withId(14).withName("Soul")
            .withItens(Arrays.asList(item3))
            .build();
        Tag tag15 = TagBuilder.aTag().withId(15).withName("Canadense")
            .withItens(Arrays.asList(item4))
            .build();
        Tag tag16 = TagBuilder.aTag().withId(16).withName("Norte Americano")
            .withItens(Arrays.asList(item5))
            .build();
        Tag tag17 = TagBuilder.aTag().withId(17).withName("Australiano")
            .withItens(Arrays.asList(item7))
            .build();
        Tag tag18 = TagBuilder.aTag().withId(18).withName("Folk")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag19 = TagBuilder.aTag().withId(19).withName("Country")
            .withItens(Arrays.asList(item8))
            .build();
        Tag tag20 = TagBuilder.aTag().withId(20).withName("Reggae")
            .withItens(Arrays.asList(item9))
            .build();
        Tag tag21 = TagBuilder.aTag().withId(21).withName("Jamaicano")
            .withItens(Arrays.asList(item9))
            .build();
        Tag tag22 = TagBuilder.aTag().withId(22).withName("Forró")
            .withItens(Arrays.asList(item10))
            .build();
        Tag tag23 = TagBuilder.aTag().withId(23).withName("Tecnobrega")
            .withItens(Arrays.asList(item10))
            .build();

        // Evaluators
        Evaluator eva4 = EvaluatorBuilder.anEvaluator().withId(1).withName("usuario4").withEmail("user4@user.com").withLogin("user4").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva5 = EvaluatorBuilder.anEvaluator().withId(2).withName("usuario5").withEmail("user5@user.com").withLogin("user5").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva6 = EvaluatorBuilder.anEvaluator().withId(3).withName("usuario6").withEmail("user6@user.com").withLogin("user6").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva7 = EvaluatorBuilder.anEvaluator().withId(4).withName("usuario7").withEmail("user7@user.com").withLogin("user7").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva8 = EvaluatorBuilder.anEvaluator().withId(5).withName("usuario8").withEmail("user8@user.com").withLogin("user8").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva9 = EvaluatorBuilder.anEvaluator().withId(6).withName("usuario9").withEmail("user9@user.com").withLogin("user9").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva10 = EvaluatorBuilder.anEvaluator().withId(7).withName("usuario10").withEmail("user10@user.com").withLogin("user10").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva11 = EvaluatorBuilder.anEvaluator().withId(8).withName("usuario11").withEmail("user11@user.com").withLogin("user11").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva12 = EvaluatorBuilder.anEvaluator().withId(9).withName("usuario12").withEmail("user12@user.com").withLogin("user12").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva13 = EvaluatorBuilder.anEvaluator().withId(10).withName("usuario13").withEmail("user13@user.com").withLogin("user13").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva14 = EvaluatorBuilder.anEvaluator().withId(11).withName("usuario14").withEmail("user14@user.com").withLogin("user14").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva15 = EvaluatorBuilder.anEvaluator().withId(12).withName("usuario15").withEmail("user15@user.com").withLogin("user15").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva16 = EvaluatorBuilder.anEvaluator().withId(13).withName("usuario16").withEmail("user16@user.com").withLogin("user16").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva17 = EvaluatorBuilder.anEvaluator().withId(14).withName("usuario17").withEmail("user17@user.com").withLogin("user17").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva18 = EvaluatorBuilder.anEvaluator().withId(15).withName("usuario18").withEmail("user18@user.com").withLogin("user18").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva19 = EvaluatorBuilder.anEvaluator().withId(16).withName("usuario19").withEmail("user19@user.com").withLogin("user19").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva20 = EvaluatorBuilder.anEvaluator().withId(17).withName("usuario20").withEmail("user20@user.com").withLogin("user20").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva21 = EvaluatorBuilder.anEvaluator().withId(18).withName("usuario21").withEmail("user21@user.com").withLogin("user21").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva22 = EvaluatorBuilder.anEvaluator().withId(19).withName("usuario22").withEmail("user22@user.com").withLogin("user22").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva23 = EvaluatorBuilder.anEvaluator().withId(20).withName("usuario23").withEmail("user23@user.com").withLogin("user23").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva25 = EvaluatorBuilder.anEvaluator().withId(21).withName("usuario25").withEmail("user25@user.com").withLogin("user25").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva26 = EvaluatorBuilder.anEvaluator().withId(22).withName("usuario26").withEmail("user26@user.com").withLogin("user26").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva27 = EvaluatorBuilder.anEvaluator().withId(23).withName("usuario27").withEmail("user27@user.com").withLogin("user27").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva28 = EvaluatorBuilder.anEvaluator().withId(24).withName("usuario28").withEmail("user28@user.com").withLogin("user28").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva31 = EvaluatorBuilder.anEvaluator().withId(25).withName("usuario31").withEmail("user31@user.com").withLogin("user31").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva32 = EvaluatorBuilder.anEvaluator().withId(26).withName("usuario32").withEmail("user32@user.com").withLogin("user32").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva33 = EvaluatorBuilder.anEvaluator().withId(27).withName("usuario33").withEmail("user33@user.com").withLogin("user33").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva34 = EvaluatorBuilder.anEvaluator().withId(28).withName("usuario34").withEmail("user34@user.com").withLogin("user34").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva35 = EvaluatorBuilder.anEvaluator().withId(29).withName("usuario35").withEmail("user35@user.com").withLogin("user35").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva36 = EvaluatorBuilder.anEvaluator().withId(30).withName("usuario36").withEmail("user36@user.com").withLogin("user36").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva37 = EvaluatorBuilder.anEvaluator().withId(31).withName("usuario37").withEmail("user37@user.com").withLogin("user37").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva38 = EvaluatorBuilder.anEvaluator().withId(32).withName("usuario38").withEmail("user38@user.com").withLogin("user38").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva39 = EvaluatorBuilder.anEvaluator().withId(33).withName("usuario39").withEmail("user39@user.com").withLogin("user39").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva40 = EvaluatorBuilder.anEvaluator().withId(34).withName("usuario40").withEmail("user40@user.com").withLogin("user40").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva41 = EvaluatorBuilder.anEvaluator().withId(35).withName("usuario41").withEmail("user41@user.com").withLogin("user41").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva42 = EvaluatorBuilder.anEvaluator().withId(36).withName("usuario42").withEmail("user42@user.com").withLogin("user42").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva43 = EvaluatorBuilder.anEvaluator().withId(37).withName("usuario43").withEmail("user43@user.com").withLogin("user43").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva44 = EvaluatorBuilder.anEvaluator().withId(38).withName("usuario44").withEmail("user44@user.com").withLogin("user44").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva45 = EvaluatorBuilder.anEvaluator().withId(39).withName("usuario45").withEmail("user45@user.com").withLogin("user45").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva46 = EvaluatorBuilder.anEvaluator().withId(40).withName("usuario46").withEmail("user46@user.com").withLogin("user46").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva47 = EvaluatorBuilder.anEvaluator().withId(41).withName("usuario47").withEmail("user47@user.com").withLogin("user47").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva48 = EvaluatorBuilder.anEvaluator().withId(42).withName("usuario48").withEmail("user48@user.com").withLogin("user48").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva49 = EvaluatorBuilder.anEvaluator().withId(43).withName("usuario49").withEmail("user49@user.com").withLogin("user49").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva50 = EvaluatorBuilder.anEvaluator().withId(44).withName("usuario50").withEmail("user50@user.com").withLogin("user50").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva51 = EvaluatorBuilder.anEvaluator().withId(45).withName("usuario51").withEmail("user51@user.com").withLogin("user51").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva52 = EvaluatorBuilder.anEvaluator().withId(46).withName("usuario52").withEmail("user52@user.com").withLogin("user52").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva53 = EvaluatorBuilder.anEvaluator().withId(47).withName("usuario53").withEmail("user53@user.com").withLogin("user53").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva54 = EvaluatorBuilder.anEvaluator().withId(48).withName("usuario54").withEmail("user54@user.com").withLogin("user54").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva55 = EvaluatorBuilder.anEvaluator().withId(49).withName("usuario55").withEmail("user55@user.com").withLogin("user55").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva56 = EvaluatorBuilder.anEvaluator().withId(50).withName("usuario56").withEmail("user56@user.com").withLogin("user56").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva57 = EvaluatorBuilder.anEvaluator().withId(51).withName("usuario57").withEmail("user57@user.com").withLogin("user57").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva58 = EvaluatorBuilder.anEvaluator().withId(52).withName("usuario58").withEmail("user58@user.com").withLogin("user58").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva59 = EvaluatorBuilder.anEvaluator().withId(53).withName("usuario59").withEmail("user59@user.com").withLogin("user59").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva60 = EvaluatorBuilder.anEvaluator().withId(54).withName("usuario60").withEmail("user60@user.com").withLogin("user60").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva61 = EvaluatorBuilder.anEvaluator().withId(55).withName("usuario61").withEmail("user61@user.com").withLogin("user61").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva62 = EvaluatorBuilder.anEvaluator().withId(56).withName("usuario62").withEmail("user62@user.com").withLogin("user62").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva63 = EvaluatorBuilder.anEvaluator().withId(57).withName("usuario63").withEmail("user63@user.com").withLogin("user63").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva64 = EvaluatorBuilder.anEvaluator().withId(58).withName("usuario64").withEmail("user64@user.com").withLogin("user64").withPassword(BcriptyUtil.encripty("123456")).build();
        Evaluator eva65 = EvaluatorBuilder.anEvaluator().withId(59).withName("usuario65").withEmail("user65@user.com").withLogin("user65").withPassword(BcriptyUtil.encripty("123456")).build();
        
        
        ItemRating itemrating0 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item1).build()).build();
        ItemRating itemrating1 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item2).build()).build();
        ItemRating itemrating2 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item3).build()).build();
        ItemRating itemrating3 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item5).build()).build();
        ItemRating itemrating4 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item7).build()).build();
        ItemRating itemrating5 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item8).build()).build();
        ItemRating itemrating6 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item9).build()).build();
        ItemRating itemrating7 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva4).withItem(item10).build()).build();
        ItemRating itemrating8 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item1).build()).build();
        ItemRating itemrating9 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item2).build()).build();
        ItemRating itemrating10 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item3).build()).build();
        ItemRating itemrating11 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item4).build()).build();
        ItemRating itemrating12 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item7).build()).build();
        ItemRating itemrating13 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item8).build()).build();
        ItemRating itemrating14 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item9).build()).build();
        ItemRating itemrating15 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva5).withItem(item10).build()).build();
        ItemRating itemrating16 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item1).build()).build();
        ItemRating itemrating17 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item2).build()).build();
        ItemRating itemrating18 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item3).build()).build();
        ItemRating itemrating19 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item4).build()).build();
        ItemRating itemrating20 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item5).build()).build();
        ItemRating itemrating21 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item6).build()).build();
        ItemRating itemrating22 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item7).build()).build();
        ItemRating itemrating23 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva6).withItem(item9).build()).build();
        ItemRating itemrating24 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item1).build()).build();
        ItemRating itemrating25 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item2).build()).build();
        ItemRating itemrating26 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item3).build()).build();
        ItemRating itemrating27 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item5).build()).build();
        ItemRating itemrating28 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item6).build()).build();
        ItemRating itemrating29 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item7).build()).build();
        ItemRating itemrating30 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item9).build()).build();
        ItemRating itemrating31 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva7).withItem(item10).build()).build();
        ItemRating itemrating32 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item1).build()).build();
        ItemRating itemrating33 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item3).build()).build();
        ItemRating itemrating34 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item4).build()).build();
        ItemRating itemrating35 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item5).build()).build();
        ItemRating itemrating36 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item7).build()).build();
        ItemRating itemrating37 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item8).build()).build();
        ItemRating itemrating38 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item9).build()).build();
        ItemRating itemrating39 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva8).withItem(item10).build()).build();
        ItemRating itemrating40 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item1).build()).build();
        ItemRating itemrating41 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item4).build()).build();
        ItemRating itemrating42 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item5).build()).build();
        ItemRating itemrating43 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item6).build()).build();
        ItemRating itemrating44 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item7).build()).build();
        ItemRating itemrating45 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item8).build()).build();
        ItemRating itemrating46 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item9).build()).build();
        ItemRating itemrating47 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(0.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva9).withItem(item10).build()).build();
        ItemRating itemrating48 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item1).build()).build();
        ItemRating itemrating49 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item3).build()).build();
        ItemRating itemrating50 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item4).build()).build();
        ItemRating itemrating51 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item5).build()).build();
        ItemRating itemrating52 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item6).build()).build();
        ItemRating itemrating53 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item7).build()).build();
        ItemRating itemrating54 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item8).build()).build();
        ItemRating itemrating55 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva10).withItem(item9).build()).build();
        ItemRating itemrating56 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item1).build()).build();
        ItemRating itemrating57 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item2).build()).build();
        ItemRating itemrating58 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item3).build()).build();
        ItemRating itemrating59 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item5).build()).build();
        ItemRating itemrating60 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item7).build()).build();
        ItemRating itemrating61 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item8).build()).build();
        ItemRating itemrating62 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item9).build()).build();
        ItemRating itemrating63 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva11).withItem(item10).build()).build();
        ItemRating itemrating64 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item1).build()).build();
        ItemRating itemrating65 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item2).build()).build();
        ItemRating itemrating66 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item3).build()).build();
        ItemRating itemrating67 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item4).build()).build();
        ItemRating itemrating68 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item5).build()).build();
        ItemRating itemrating69 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item6).build()).build();
        ItemRating itemrating70 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item9).build()).build();
        ItemRating itemrating71 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva12).withItem(item10).build()).build();
        ItemRating itemrating72 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item1).build()).build();
        ItemRating itemrating73 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item3).build()).build();
        ItemRating itemrating74 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item4).build()).build();
        ItemRating itemrating75 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item5).build()).build();
        ItemRating itemrating76 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item7).build()).build();
        ItemRating itemrating77 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item8).build()).build();
        ItemRating itemrating78 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item9).build()).build();
        ItemRating itemrating79 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva13).withItem(item10).build()).build();
        ItemRating itemrating80 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item1).build()).build();
        ItemRating itemrating81 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item2).build()).build();
        ItemRating itemrating82 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item3).build()).build();
        ItemRating itemrating83 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item4).build()).build();
        ItemRating itemrating84 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item6).build()).build();
        ItemRating itemrating85 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item8).build()).build();
        ItemRating itemrating86 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item9).build()).build();
        ItemRating itemrating87 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva14).withItem(item10).build()).build();
        ItemRating itemrating88 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item1).build()).build();
        ItemRating itemrating89 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item2).build()).build();
        ItemRating itemrating90 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item3).build()).build();
        ItemRating itemrating91 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item4).build()).build();
        ItemRating itemrating92 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item6).build()).build();
        ItemRating itemrating93 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item7).build()).build();
        ItemRating itemrating94 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item9).build()).build();
        ItemRating itemrating95 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva15).withItem(item10).build()).build();
        ItemRating itemrating96 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item1).build()).build();
        ItemRating itemrating97 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item3).build()).build();
        ItemRating itemrating98 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item4).build()).build();
        ItemRating itemrating99 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item5).build()).build();
        ItemRating itemrating100 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item6).build()).build();
        ItemRating itemrating101 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item7).build()).build();
        ItemRating itemrating102 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item8).build()).build();
        ItemRating itemrating103 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva16).withItem(item9).build()).build();
        ItemRating itemrating104 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item1).build()).build();
        ItemRating itemrating105 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item3).build()).build();
        ItemRating itemrating106 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item4).build()).build();
        ItemRating itemrating107 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item6).build()).build();
        ItemRating itemrating108 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item7).build()).build();
        ItemRating itemrating109 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item8).build()).build();
        ItemRating itemrating110 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item9).build()).build();
        ItemRating itemrating111 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva17).withItem(item10).build()).build();
        ItemRating itemrating112 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item1).build()).build();
        ItemRating itemrating113 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item2).build()).build();
        ItemRating itemrating114 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item3).build()).build();
        ItemRating itemrating115 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item4).build()).build();
        ItemRating itemrating116 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item5).build()).build();
        ItemRating itemrating117 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item8).build()).build();
        ItemRating itemrating118 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item9).build()).build();
        ItemRating itemrating119 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva18).withItem(item10).build()).build();
        ItemRating itemrating120 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item1).build()).build();
        ItemRating itemrating121 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item2).build()).build();
        ItemRating itemrating122 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item3).build()).build();
        ItemRating itemrating123 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item6).build()).build();
        ItemRating itemrating124 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item7).build()).build();
        ItemRating itemrating125 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item8).build()).build();
        ItemRating itemrating126 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item9).build()).build();
        ItemRating itemrating127 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva19).withItem(item10).build()).build();
        ItemRating itemrating128 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item1).build()).build();
        ItemRating itemrating129 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item2).build()).build();
        ItemRating itemrating130 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item5).build()).build();
        ItemRating itemrating131 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item6).build()).build();
        ItemRating itemrating132 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item7).build()).build();
        ItemRating itemrating133 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item8).build()).build();
        ItemRating itemrating134 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item9).build()).build();
        ItemRating itemrating135 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva20).withItem(item10).build()).build();
        ItemRating itemrating136 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item1).build()).build();
        ItemRating itemrating137 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item3).build()).build();
        ItemRating itemrating138 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item4).build()).build();
        ItemRating itemrating139 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item5).build()).build();
        ItemRating itemrating140 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item6).build()).build();
        ItemRating itemrating141 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item8).build()).build();
        ItemRating itemrating142 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item9).build()).build();
        ItemRating itemrating143 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva21).withItem(item10).build()).build();
        ItemRating itemrating144 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item1).build()).build();
        ItemRating itemrating145 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item2).build()).build();
        ItemRating itemrating146 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item3).build()).build();
        ItemRating itemrating147 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item4).build()).build();
        ItemRating itemrating148 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item5).build()).build();
        ItemRating itemrating149 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item6).build()).build();
        ItemRating itemrating150 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item8).build()).build();
        ItemRating itemrating151 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva22).withItem(item10).build()).build();
        ItemRating itemrating152 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item1).build()).build();
        ItemRating itemrating153 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item2).build()).build();
        ItemRating itemrating154 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item3).build()).build();
        ItemRating itemrating155 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item5).build()).build();
        ItemRating itemrating156 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item6).build()).build();
        ItemRating itemrating157 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item7).build()).build();
        ItemRating itemrating158 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item8).build()).build();
        ItemRating itemrating159 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva23).withItem(item9).build()).build();
        ItemRating itemrating160 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item1).build()).build();
        ItemRating itemrating161 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item3).build()).build();
        ItemRating itemrating162 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item4).build()).build();
        ItemRating itemrating163 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item5).build()).build();
        ItemRating itemrating164 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item6).build()).build();
        ItemRating itemrating165 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item7).build()).build();
        ItemRating itemrating166 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item9).build()).build();
        ItemRating itemrating167 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva25).withItem(item10).build()).build();
        ItemRating itemrating168 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item1).build()).build();
        ItemRating itemrating169 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item2).build()).build();
        ItemRating itemrating170 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item3).build()).build();
        ItemRating itemrating171 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item4).build()).build();
        ItemRating itemrating172 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item5).build()).build();
        ItemRating itemrating173 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item7).build()).build();
        ItemRating itemrating174 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item8).build()).build();
        ItemRating itemrating175 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva26).withItem(item10).build()).build();
        ItemRating itemrating176 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item1).build()).build();
        ItemRating itemrating177 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item2).build()).build();
        ItemRating itemrating178 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item3).build()).build();
        ItemRating itemrating179 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item5).build()).build();
        ItemRating itemrating180 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item6).build()).build();
        ItemRating itemrating181 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item7).build()).build();
        ItemRating itemrating182 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item8).build()).build();
        ItemRating itemrating183 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva27).withItem(item10).build()).build();
        ItemRating itemrating184 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item1).build()).build();
        ItemRating itemrating185 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item3).build()).build();
        ItemRating itemrating186 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item4).build()).build();
        ItemRating itemrating187 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item6).build()).build();
        ItemRating itemrating188 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item7).build()).build();
        ItemRating itemrating189 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item8).build()).build();
        ItemRating itemrating190 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item9).build()).build();
        ItemRating itemrating191 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva28).withItem(item10).build()).build();
        ItemRating itemrating192 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item1).build()).build();
        ItemRating itemrating193 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item2).build()).build();
        ItemRating itemrating194 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item3).build()).build();
        ItemRating itemrating195 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item4).build()).build();
        ItemRating itemrating196 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item5).build()).build();
        ItemRating itemrating197 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item7).build()).build();
        ItemRating itemrating198 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item8).build()).build();
        ItemRating itemrating199 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva31).withItem(item9).build()).build();
        ItemRating itemrating200 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item1).build()).build();
        ItemRating itemrating201 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item2).build()).build();
        ItemRating itemrating202 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item3).build()).build();
        ItemRating itemrating203 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item4).build()).build();
        ItemRating itemrating204 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item5).build()).build();
        ItemRating itemrating205 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item6).build()).build();
        ItemRating itemrating206 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item8).build()).build();
        ItemRating itemrating207 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva32).withItem(item10).build()).build();
        ItemRating itemrating208 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item1).build()).build();
        ItemRating itemrating209 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item3).build()).build();
        ItemRating itemrating210 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item4).build()).build();
        ItemRating itemrating211 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item6).build()).build();
        ItemRating itemrating212 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item7).build()).build();
        ItemRating itemrating213 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item8).build()).build();
        ItemRating itemrating214 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item9).build()).build();
        ItemRating itemrating215 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva33).withItem(item10).build()).build();
        ItemRating itemrating216 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item1).build()).build();
        ItemRating itemrating217 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item2).build()).build();
        ItemRating itemrating218 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item3).build()).build();
        ItemRating itemrating219 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item4).build()).build();
        ItemRating itemrating220 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item6).build()).build();
        ItemRating itemrating221 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item8).build()).build();
        ItemRating itemrating222 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item9).build()).build();
        ItemRating itemrating223 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva34).withItem(item10).build()).build();
        ItemRating itemrating224 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item1).build()).build();
        ItemRating itemrating225 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item2).build()).build();
        ItemRating itemrating226 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item3).build()).build();
        ItemRating itemrating227 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item4).build()).build();
        ItemRating itemrating228 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item6).build()).build();
        ItemRating itemrating229 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item7).build()).build();
        ItemRating itemrating230 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item8).build()).build();
        ItemRating itemrating231 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva35).withItem(item10).build()).build();
        ItemRating itemrating232 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item1).build()).build();
        ItemRating itemrating233 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item2).build()).build();
        ItemRating itemrating234 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item4).build()).build();
        ItemRating itemrating235 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item5).build()).build();
        ItemRating itemrating236 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item6).build()).build();
        ItemRating itemrating237 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item7).build()).build();
        ItemRating itemrating238 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item9).build()).build();
        ItemRating itemrating239 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva36).withItem(item10).build()).build();
        ItemRating itemrating240 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item1).build()).build();
        ItemRating itemrating241 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item2).build()).build();
        ItemRating itemrating242 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item3).build()).build();
        ItemRating itemrating243 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item4).build()).build();
        ItemRating itemrating244 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item6).build()).build();
        ItemRating itemrating245 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item7).build()).build();
        ItemRating itemrating246 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item8).build()).build();
        ItemRating itemrating247 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva37).withItem(item10).build()).build();
        ItemRating itemrating248 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item1).build()).build();
        ItemRating itemrating249 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item2).build()).build();
        ItemRating itemrating250 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item4).build()).build();
        ItemRating itemrating251 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item5).build()).build();
        ItemRating itemrating252 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item6).build()).build();
        ItemRating itemrating253 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item7).build()).build();
        ItemRating itemrating254 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item8).build()).build();
        ItemRating itemrating255 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva38).withItem(item10).build()).build();
        ItemRating itemrating256 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item1).build()).build();
        ItemRating itemrating257 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item2).build()).build();
        ItemRating itemrating258 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item3).build()).build();
        ItemRating itemrating259 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item4).build()).build();
        ItemRating itemrating260 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item6).build()).build();
        ItemRating itemrating261 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item8).build()).build();
        ItemRating itemrating262 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item9).build()).build();
        ItemRating itemrating263 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva39).withItem(item10).build()).build();
        ItemRating itemrating264 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item1).build()).build();
        ItemRating itemrating265 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item2).build()).build();
        ItemRating itemrating266 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item3).build()).build();
        ItemRating itemrating267 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item5).build()).build();
        ItemRating itemrating268 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item7).build()).build();
        ItemRating itemrating269 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item8).build()).build();
        ItemRating itemrating270 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item9).build()).build();
        ItemRating itemrating271 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva40).withItem(item10).build()).build();
        ItemRating itemrating272 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item1).build()).build();
        ItemRating itemrating273 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item2).build()).build();
        ItemRating itemrating274 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item3).build()).build();
        ItemRating itemrating275 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item4).build()).build();
        ItemRating itemrating276 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item5).build()).build();
        ItemRating itemrating277 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item6).build()).build();
        ItemRating itemrating278 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item7).build()).build();
        ItemRating itemrating279 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva41).withItem(item9).build()).build();
        ItemRating itemrating280 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item1).build()).build();
        ItemRating itemrating281 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item2).build()).build();
        ItemRating itemrating282 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item3).build()).build();
        ItemRating itemrating283 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item4).build()).build();
        ItemRating itemrating284 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item6).build()).build();
        ItemRating itemrating285 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item8).build()).build();
        ItemRating itemrating286 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item9).build()).build();
        ItemRating itemrating287 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva42).withItem(item10).build()).build();
        ItemRating itemrating288 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item1).build()).build();
        ItemRating itemrating289 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item2).build()).build();
        ItemRating itemrating290 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item3).build()).build();
        ItemRating itemrating291 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item5).build()).build();
        ItemRating itemrating292 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item6).build()).build();
        ItemRating itemrating293 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item7).build()).build();
        ItemRating itemrating294 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item9).build()).build();
        ItemRating itemrating295 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva43).withItem(item10).build()).build();
        ItemRating itemrating296 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item1).build()).build();
        ItemRating itemrating297 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item2).build()).build();
        ItemRating itemrating298 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item3).build()).build();
        ItemRating itemrating299 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item5).build()).build();
        ItemRating itemrating300 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item6).build()).build();
        ItemRating itemrating301 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item7).build()).build();
        ItemRating itemrating302 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item8).build()).build();
        ItemRating itemrating303 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva44).withItem(item9).build()).build();
        ItemRating itemrating304 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item1).build()).build();
        ItemRating itemrating305 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item2).build()).build();
        ItemRating itemrating306 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item3).build()).build();
        ItemRating itemrating307 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item4).build()).build();
        ItemRating itemrating308 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item5).build()).build();
        ItemRating itemrating309 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item6).build()).build();
        ItemRating itemrating310 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item9).build()).build();
        ItemRating itemrating311 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva45).withItem(item10).build()).build();
        ItemRating itemrating312 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item1).build()).build();
        ItemRating itemrating313 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item2).build()).build();
        ItemRating itemrating314 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item3).build()).build();
        ItemRating itemrating315 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item4).build()).build();
        ItemRating itemrating316 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item6).build()).build();
        ItemRating itemrating317 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item7).build()).build();
        ItemRating itemrating318 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item9).build()).build();
        ItemRating itemrating319 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva46).withItem(item10).build()).build();
        ItemRating itemrating320 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item1).build()).build();
        ItemRating itemrating321 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item2).build()).build();
        ItemRating itemrating322 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item3).build()).build();
        ItemRating itemrating323 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item4).build()).build();
        ItemRating itemrating324 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item6).build()).build();
        ItemRating itemrating325 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item7).build()).build();
        ItemRating itemrating326 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item8).build()).build();
        ItemRating itemrating327 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva47).withItem(item9).build()).build();
        ItemRating itemrating328 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item1).build()).build();
        ItemRating itemrating329 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item2).build()).build();
        ItemRating itemrating330 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item3).build()).build();
        ItemRating itemrating331 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item4).build()).build();
        ItemRating itemrating332 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item7).build()).build();
        ItemRating itemrating333 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item8).build()).build();
        ItemRating itemrating334 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item9).build()).build();
        ItemRating itemrating335 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva48).withItem(item10).build()).build();
        ItemRating itemrating336 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item1).build()).build();
        ItemRating itemrating337 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item2).build()).build();
        ItemRating itemrating338 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item3).build()).build();
        ItemRating itemrating339 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item4).build()).build();
        ItemRating itemrating340 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item5).build()).build();
        ItemRating itemrating341 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item6).build()).build();
        ItemRating itemrating342 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item9).build()).build();
        ItemRating itemrating343 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva49).withItem(item10).build()).build();
        ItemRating itemrating344 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item1).build()).build();
        ItemRating itemrating345 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item2).build()).build();
        ItemRating itemrating346 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item4).build()).build();
        ItemRating itemrating347 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item5).build()).build();
        ItemRating itemrating348 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item7).build()).build();
        ItemRating itemrating349 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item8).build()).build();
        ItemRating itemrating350 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item9).build()).build();
        ItemRating itemrating351 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva50).withItem(item10).build()).build();
        ItemRating itemrating352 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item1).build()).build();
        ItemRating itemrating353 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item2).build()).build();
        ItemRating itemrating354 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item3).build()).build();
        ItemRating itemrating355 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item4).build()).build();
        ItemRating itemrating356 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item5).build()).build();
        ItemRating itemrating357 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item6).build()).build();
        ItemRating itemrating358 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item7).build()).build();
        ItemRating itemrating359 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva51).withItem(item8).build()).build();
        ItemRating itemrating360 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item1).build()).build();
        ItemRating itemrating361 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item2).build()).build();
        ItemRating itemrating362 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item3).build()).build();
        ItemRating itemrating363 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item4).build()).build();
        ItemRating itemrating364 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item5).build()).build();
        ItemRating itemrating365 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item6).build()).build();
        ItemRating itemrating366 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item9).build()).build();
        ItemRating itemrating367 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva52).withItem(item10).build()).build();
        ItemRating itemrating368 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item1).build()).build();
        ItemRating itemrating369 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item2).build()).build();
        ItemRating itemrating370 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item3).build()).build();
        ItemRating itemrating371 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item4).build()).build();
        ItemRating itemrating372 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item6).build()).build();
        ItemRating itemrating373 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item8).build()).build();
        ItemRating itemrating374 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item9).build()).build();
        ItemRating itemrating375 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva53).withItem(item10).build()).build();
        ItemRating itemrating376 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item1).build()).build();
        ItemRating itemrating377 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item2).build()).build();
        ItemRating itemrating378 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item3).build()).build();
        ItemRating itemrating379 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item6).build()).build();
        ItemRating itemrating380 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item7).build()).build();
        ItemRating itemrating381 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item8).build()).build();
        ItemRating itemrating382 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item9).build()).build();
        ItemRating itemrating383 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva54).withItem(item10).build()).build();
        ItemRating itemrating384 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item1).build()).build();
        ItemRating itemrating385 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item2).build()).build();
        ItemRating itemrating386 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item3).build()).build();
        ItemRating itemrating387 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item4).build()).build();
        ItemRating itemrating388 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item5).build()).build();
        ItemRating itemrating389 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item6).build()).build();
        ItemRating itemrating390 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item8).build()).build();
        ItemRating itemrating391 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva55).withItem(item9).build()).build();
        ItemRating itemrating392 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item1).build()).build();
        ItemRating itemrating393 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item3).build()).build();
        ItemRating itemrating394 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item4).build()).build();
        ItemRating itemrating395 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item5).build()).build();
        ItemRating itemrating396 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item6).build()).build();
        ItemRating itemrating397 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item7).build()).build();
        ItemRating itemrating398 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item9).build()).build();
        ItemRating itemrating399 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva56).withItem(item10).build()).build();
        ItemRating itemrating400 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item1).build()).build();
        ItemRating itemrating401 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item2).build()).build();
        ItemRating itemrating402 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item4).build()).build();
        ItemRating itemrating403 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item5).build()).build();
        ItemRating itemrating404 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item6).build()).build();
        ItemRating itemrating405 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item7).build()).build();
        ItemRating itemrating406 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(0.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item9).build()).build();
        ItemRating itemrating407 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva57).withItem(item10).build()).build();
        ItemRating itemrating408 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item1).build()).build();
        ItemRating itemrating409 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item4).build()).build();
        ItemRating itemrating410 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item5).build()).build();
        ItemRating itemrating411 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item6).build()).build();
        ItemRating itemrating412 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item7).build()).build();
        ItemRating itemrating413 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item8).build()).build();
        ItemRating itemrating414 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item9).build()).build();
        ItemRating itemrating415 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva58).withItem(item10).build()).build();
        ItemRating itemrating416 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item1).build()).build();
        ItemRating itemrating417 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item2).build()).build();
        ItemRating itemrating418 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item3).build()).build();
        ItemRating itemrating419 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item4).build()).build();
        ItemRating itemrating420 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item5).build()).build();
        ItemRating itemrating421 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item6).build()).build();
        ItemRating itemrating422 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item8).build()).build();
        ItemRating itemrating423 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva59).withItem(item9).build()).build();
        ItemRating itemrating424 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item1).build()).build();
        ItemRating itemrating425 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item2).build()).build();
        ItemRating itemrating426 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item3).build()).build();
        ItemRating itemrating427 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item4).build()).build();
        ItemRating itemrating428 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item5).build()).build();
        ItemRating itemrating429 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item8).build()).build();
        ItemRating itemrating430 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item9).build()).build();
        ItemRating itemrating431 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva60).withItem(item10).build()).build();
        ItemRating itemrating432 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item1).build()).build();
        ItemRating itemrating433 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item4).build()).build();
        ItemRating itemrating434 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item5).build()).build();
        ItemRating itemrating435 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item6).build()).build();
        ItemRating itemrating436 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item7).build()).build();
        ItemRating itemrating437 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item8).build()).build();
        ItemRating itemrating438 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item9).build()).build();
        ItemRating itemrating439 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(1.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva61).withItem(item10).build()).build();
        ItemRating itemrating440 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item1).build()).build();
        ItemRating itemrating441 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item2).build()).build();
        ItemRating itemrating442 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item4).build()).build();
        ItemRating itemrating443 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item5).build()).build();
        ItemRating itemrating444 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item7).build()).build();
        ItemRating itemrating445 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item8).build()).build();
        ItemRating itemrating446 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item9).build()).build();
        ItemRating itemrating447 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva62).withItem(item10).build()).build();
        ItemRating itemrating448 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item1).build()).build();
        ItemRating itemrating449 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item2).build()).build();
        ItemRating itemrating450 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item3).build()).build();
        ItemRating itemrating451 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item6).build()).build();
        ItemRating itemrating452 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item7).build()).build();
        ItemRating itemrating453 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item8).build()).build();
        ItemRating itemrating454 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item9).build()).build();
        ItemRating itemrating455 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(2.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva63).withItem(item10).build()).build();
        ItemRating itemrating456 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item1).build()).build();
        ItemRating itemrating457 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item2).build()).build();
        ItemRating itemrating458 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item3).build()).build();
        ItemRating itemrating459 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item4).build()).build();
        ItemRating itemrating460 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item6).build()).build();
        ItemRating itemrating461 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item8).build()).build();
        ItemRating itemrating462 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item9).build()).build();
        ItemRating itemrating463 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva64).withItem(item10).build()).build();
        ItemRating itemrating464 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item1).build()).build();
        ItemRating itemrating465 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item2).build()).build();
        ItemRating itemrating466 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item3).build()).build();
        ItemRating itemrating467 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item4).build()).build();
        ItemRating itemrating468 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item5).build()).build();
        ItemRating itemrating469 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(5.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item7).build()).build();
        ItemRating itemrating470 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(4.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item9).build()).build();
        ItemRating itemrating471 = ItemRatingBuilder.anItemRating().withDate(LocalDateTime.now()).withScore(3.0).withId(ItemRatingPKBuilder.anItemRatingPK().withEvaluator(eva65).withItem(item10).build()).build();
        
        projectRepository.save(testProject);

        List<Evaluator> listEvaluators = Arrays.asList( eva4,  eva5,  eva6,  eva7,  eva8,  eva9,  eva10,  eva11,  eva12,  eva13,  eva14,  eva15,  eva16,  eva17,  eva18,  eva19,  eva20,  eva21,  eva22,  eva23,  eva25,  eva26,  eva27,  eva28,  eva31,  eva32,  eva33,  eva34,  eva35,  eva36,  eva37,  eva38,  eva39,  eva40,  eva41,  eva42,  eva43,  eva44,  eva45,  eva46,  eva47,  eva48,  eva49,  eva50,  eva51,  eva52,  eva53,  eva54,  eva55,  eva56,  eva57,  eva58,  eva59,  eva60,  eva61,  eva62,  eva63,  eva64,  eva65);
        List<Tag> listTags = Arrays.asList( tag1,  tag2,  tag3,  tag4,  tag5,  tag6,  tag7,  tag8,  tag9,  tag10,  tag11,  tag12,  tag13,  tag14,  tag15,  tag16,  tag17,  tag18,  tag19,  tag20,  tag21,  tag22,  tag23  );
        List<ItemRating> listItemRatings = Arrays.asList( itemrating0,  itemrating1,  itemrating2,  itemrating3,  itemrating4,  itemrating5,  itemrating6,  itemrating7,  itemrating8,  itemrating9,  itemrating10,  itemrating11,  itemrating12,  itemrating13,  itemrating14,  itemrating15,  itemrating16,  itemrating17,  itemrating18,  itemrating19,  itemrating20,  itemrating21,  itemrating22,  itemrating23,  itemrating24,  itemrating25,  itemrating26,  itemrating27,  itemrating28,  itemrating29,  itemrating30,  itemrating31,  itemrating32,  itemrating33,  itemrating34,  itemrating35,  itemrating36,  itemrating37,  itemrating38,  itemrating39,  itemrating40,  itemrating41,  itemrating42,  itemrating43,  itemrating44,  itemrating45,  itemrating46,  itemrating47,  itemrating48,  itemrating49,  itemrating50,  itemrating51,  itemrating52,  itemrating53,  itemrating54,  itemrating55,  itemrating56,  itemrating57,  itemrating58,  itemrating59,  itemrating60,  itemrating61,  itemrating62,  itemrating63,  itemrating64,  itemrating65,  itemrating66,  itemrating67,  itemrating68,  itemrating69,  itemrating70,  itemrating71,  itemrating72,  itemrating73,  itemrating74,  itemrating75,  itemrating76,  itemrating77,  itemrating78,  itemrating79,  itemrating80,  itemrating81,  itemrating82,  itemrating83,  itemrating84,  itemrating85,  itemrating86,  itemrating87,  itemrating88,  itemrating89,  itemrating90,  itemrating91,  itemrating92,  itemrating93,  itemrating94,  itemrating95,  itemrating96,  itemrating97,  itemrating98,  itemrating99,  itemrating100,  itemrating101,  itemrating102,  itemrating103,  itemrating104,  itemrating105,  itemrating106,  itemrating107,  itemrating108,  itemrating109,  itemrating110,  itemrating111,  itemrating112,  itemrating113,  itemrating114,  itemrating115,  itemrating116,  itemrating117,  itemrating118,  itemrating119,  itemrating120,  itemrating121,  itemrating122,  itemrating123,  itemrating124,  itemrating125,  itemrating126,  itemrating127,  itemrating128,  itemrating129,  itemrating130,  itemrating131,  itemrating132,  itemrating133,  itemrating134,  itemrating135,  itemrating136,  itemrating137,  itemrating138,  itemrating139,  itemrating140,  itemrating141,  itemrating142,  itemrating143,  itemrating144,  itemrating145,  itemrating146,  itemrating147,  itemrating148,  itemrating149,  itemrating150,  itemrating151,  itemrating152,  itemrating153,  itemrating154,  itemrating155,  itemrating156,  itemrating157,  itemrating158,  itemrating159,  itemrating160,  itemrating161,  itemrating162,  itemrating163,  itemrating164,  itemrating165,  itemrating166,  itemrating167,  itemrating168,  itemrating169,  itemrating170,  itemrating171,  itemrating172,  itemrating173,  itemrating174,  itemrating175,  itemrating176,  itemrating177,  itemrating178,  itemrating179,  itemrating180,  itemrating181,  itemrating182,  itemrating183,  itemrating184,  itemrating185,  itemrating186,  itemrating187,  itemrating188,  itemrating189,  itemrating190,  itemrating191,  itemrating192,  itemrating193,  itemrating194,  itemrating195,  itemrating196,  itemrating197,  itemrating198,  itemrating199,  itemrating200,  itemrating201,  itemrating202,  itemrating203,  itemrating204,  itemrating205,  itemrating206,  itemrating207,  itemrating208,  itemrating209,  itemrating210,  itemrating211,  itemrating212,  itemrating213,  itemrating214,  itemrating215,  itemrating216,  itemrating217,  itemrating218,  itemrating219,  itemrating220,  itemrating221,  itemrating222,  itemrating223,  itemrating224,  itemrating225,  itemrating226,  itemrating227,  itemrating228,  itemrating229,  itemrating230,  itemrating231,  itemrating232,  itemrating233,  itemrating234,  itemrating235,  itemrating236,  itemrating237,  itemrating238,  itemrating239,  itemrating240,  itemrating241,  itemrating242,  itemrating243,  itemrating244,  itemrating245,  itemrating246,  itemrating247,  itemrating248,  itemrating249,  itemrating250,  itemrating251,  itemrating252,  itemrating253,  itemrating254,  itemrating255,  itemrating256,  itemrating257,  itemrating258,  itemrating259,  itemrating260,  itemrating261,  itemrating262,  itemrating263,  itemrating264,  itemrating265,  itemrating266,  itemrating267,  itemrating268,  itemrating269,  itemrating270,  itemrating271,  itemrating272,  itemrating273,  itemrating274,  itemrating275,  itemrating276,  itemrating277,  itemrating278,  itemrating279,  itemrating280,  itemrating281,  itemrating282,  itemrating283,  itemrating284,  itemrating285,  itemrating286,  itemrating287,  itemrating288,  itemrating289,  itemrating290,  itemrating291,  itemrating292,  itemrating293,  itemrating294,  itemrating295,  itemrating296,  itemrating297,  itemrating298,  itemrating299,  itemrating300,  itemrating301,  itemrating302,  itemrating303,  itemrating304,  itemrating305,  itemrating306,  itemrating307,  itemrating308,  itemrating309,  itemrating310,  itemrating311,  itemrating312,  itemrating313,  itemrating314,  itemrating315,  itemrating316,  itemrating317,  itemrating318,  itemrating319,  itemrating320,  itemrating321,  itemrating322,  itemrating323,  itemrating324,  itemrating325,  itemrating326,  itemrating327,  itemrating328,  itemrating329,  itemrating330,  itemrating331,  itemrating332,  itemrating333,  itemrating334,  itemrating335,  itemrating336,  itemrating337,  itemrating338,  itemrating339,  itemrating340,  itemrating341,  itemrating342,  itemrating343,  itemrating344,  itemrating345,  itemrating346,  itemrating347,  itemrating348,  itemrating349,  itemrating350,  itemrating351,  itemrating352,  itemrating353,  itemrating354,  itemrating355,  itemrating356,  itemrating357,  itemrating358,  itemrating359,  itemrating360,  itemrating361,  itemrating362,  itemrating363,  itemrating364,  itemrating365,  itemrating366,  itemrating367,  itemrating368,  itemrating369,  itemrating370,  itemrating371,  itemrating372,  itemrating373,  itemrating374,  itemrating375,  itemrating376,  itemrating377,  itemrating378,  itemrating379,  itemrating380,  itemrating381,  itemrating382,  itemrating383,  itemrating384,  itemrating385,  itemrating386,  itemrating387,  itemrating388,  itemrating389,  itemrating390,  itemrating391,  itemrating392,  itemrating393,  itemrating394,  itemrating395,  itemrating396,  itemrating397,  itemrating398,  itemrating399,  itemrating400,  itemrating401,  itemrating402,  itemrating403,  itemrating404,  itemrating405,  itemrating406,  itemrating407,  itemrating408,  itemrating409,  itemrating410,  itemrating411,  itemrating412,  itemrating413,  itemrating414,  itemrating415,  itemrating416,  itemrating417,  itemrating418,  itemrating419,  itemrating420,  itemrating421,  itemrating422,  itemrating423,  itemrating424,  itemrating425,  itemrating426,  itemrating427,  itemrating428,  itemrating429,  itemrating430,  itemrating431,  itemrating432,  itemrating433,  itemrating434,  itemrating435,  itemrating436,  itemrating437,  itemrating438,  itemrating439,  itemrating440,  itemrating441,  itemrating442,  itemrating443,  itemrating444,  itemrating445,  itemrating446,  itemrating447,  itemrating448,  itemrating449,  itemrating450,  itemrating451,  itemrating452,  itemrating453,  itemrating454,  itemrating455,  itemrating456,  itemrating457,  itemrating458,  itemrating459,  itemrating460,  itemrating461,  itemrating462,  itemrating463,  itemrating464,  itemrating465,  itemrating466,  itemrating467,  itemrating468,  itemrating469,  itemrating470,  itemrating471  );


        for(Evaluator ev : listEvaluators){
            ev.setProjects(Arrays.asList(testProject));
        }
        evaluatorRepository.saveAll(listEvaluators);

        List<Item> listItems = Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);
        itemRepository.saveAll(listItems);

        tagRepository.saveAll(listTags);
        
        itemRatingRepository.saveAll(listItemRatings);
    }

    private void createTestProject() {
        
        Admin admin = AdminBuilder.anAdmin()
            .withId(1)
            .withName("Mr. Admin")
            .withLogin("adm")
            .withEmail("admin@admin.com")
            .withPassword(BcriptyUtil.encripty("123456"))
            .build();
        adminRepository.save(admin);

        Project testProject = ProjectBuilder.aProject()
            .withId(1)
            .withDate(LocalDate.now())
            .withDescription("Test Project for Debugging")
            .withName("test")
            .withAdmin(admin)
            .withLastMatrixId(0)
            .build();

        Item celular = ItemBuilder.anItem()
            .withId(1)
            .withName("Celular")
            .withDescription("Um celular")
            .withProject(testProject)
            .build();
        Item guitarra = ItemBuilder.anItem()
            .withId(2)
            .withName("Guitarra")
            .withDescription("Uma guitarra")
            .withProject(testProject)
            .build();
        Item cadeira = ItemBuilder.anItem()
            .withId(3)
            .withName("Cadeira")
            .withDescription("Uma cadeira")
            .withProject(testProject)
            .build();
        Item bateria = ItemBuilder.anItem()
            .withId(4)
            .withName("Bateria")
            .withDescription("Uma bateria")
            .withProject(testProject)
            .build();

        Tag musical = TagBuilder.aTag()
            .withId(1)
            .withName("Musical")
            .withItens(Arrays.asList(guitarra,bateria))
            .build();
        Tag tecnologia = TagBuilder.aTag()
            .withId(2)
            .withName("Tecnologia")
            .withItens(Arrays.asList(celular))
            .build();
        Tag lazer = TagBuilder.aTag()
            .withId(3)
            .withName("Lazer")
            .withItens(Arrays.asList(celular,guitarra,bateria))
            .build();
        Tag precoAlto = TagBuilder.aTag()
            .withId(4)
            .withName("Preço Alto")
            .withItens(Arrays.asList(celular,guitarra,cadeira,bateria))
            .build();
        Tag grande = TagBuilder.aTag()
            .withId(5)
            .withName("Grande")
            .withItens(Arrays.asList(guitarra,cadeira,bateria))
            .build();
        
        Evaluator alberto = EvaluatorBuilder.anEvaluator()
            .withId(1)
            .withName("Alberto").withEmail("Alberto@alberto.com")
            .withLogin("alberto").withPassword(BcriptyUtil.encripty("123456"))
            .build();
        Evaluator bianca = EvaluatorBuilder.anEvaluator()
            .withId(2)
            .withName("Bianca").withEmail("Bianca@bianca.com")
            .withLogin("bianca").withPassword(BcriptyUtil.encripty("123456"))
            .build();
        Evaluator carlos = EvaluatorBuilder.anEvaluator()
            .withId(3)
            .withName("Carlos").withEmail("Carlos@carlos.com")
            .withLogin("carlos").withPassword(BcriptyUtil.encripty("123456"))
            .build();
        Evaluator daniele = EvaluatorBuilder.anEvaluator()
            .withId(4)
            .withName("Daniele").withEmail("Daniele@daniele.com")
            .withLogin("daniele").withPassword(BcriptyUtil.encripty("123456"))
            .build();
        
        ItemRating albCel = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(4.5)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(alberto).withItem(celular)
                .build())
            .build();
        ItemRating albGui = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(4.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(alberto).withItem(guitarra)
                .build())
            .build();
        ItemRating albCad = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(5.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(alberto).withItem(cadeira)
                .build())
            .build();
        ItemRating biaGui = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(3.5)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(bianca).withItem(guitarra)
                .build())
            .build();
        ItemRating biaCad = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(1.5)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(bianca).withItem(cadeira)
                .build())
            .build();
        ItemRating biaBat = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(4.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(bianca).withItem(bateria)
                .build())
            .build();
        ItemRating carCel = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(5.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(carlos).withItem(celular)
                .build())
            .build();
        ItemRating carGui = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(3.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(carlos).withItem(guitarra)
                .build())
            .build();
        ItemRating danCad = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(4.0)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(daniele).withItem(cadeira)
                .build())
            .build();
        ItemRating danBat = ItemRatingBuilder.anItemRating()
            .withDate(LocalDateTime.now())
            .withScore(2.5)
            .withId(ItemRatingPKBuilder.anItemRatingPK()
                .withEvaluator(daniele).withItem(bateria)
                .build())
            .build();

        projectRepository.save(testProject);

        List<Evaluator> listEvaluators = Arrays.asList(alberto,bianca,carlos,daniele);
        for(Evaluator ev : listEvaluators){
            ev.setProjects(Arrays.asList(testProject));
        }
        evaluatorRepository.saveAll(listEvaluators);

        List<Item> listItems = Arrays.asList(celular, guitarra, cadeira, bateria);
        itemRepository.saveAll(listItems);

        List<Tag> listTags = Arrays.asList(musical, tecnologia, lazer, precoAlto, grande);
        tagRepository.saveAll(listTags);

        List<ItemRating> listItemRatings = Arrays.asList(
            albCel,albGui,albCad,
                   biaGui,biaCad,biaBat,
            carCel,carGui,
                          danCad,danBat);
        itemRatingRepository.saveAll(listItemRatings);


    }

    private void createApiUserAdmin() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(adminProfile);
        profiles.add(userProfile);

        ApiUser apiUser = ApiUserBuilder.anApiUser()
                .withId(1)
                .withEmail("admin@email.com")
                .withName("admin")
                .withLogin("admin")
                .withPassword(BcriptyUtil.encripty("123456"))
                .withProfiles(profiles)
                .withIsAdmin(true)
                .build();

        apiUserRepository.save(apiUser);
    }

    private void createApiUserClient() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(userProfile);

        ApiUser apiUser = ApiUserBuilder.anApiUser()
                .withId(2)
                .withEmail("client@email.com")
                .withName("client")
                .withLogin("client")
                .withPassword(BcriptyUtil.encripty("123456"))
                .withProfiles(profiles)
                .withIsAdmin(true)
                .build();

        apiUserRepository.save(apiUser);
    }

    private void createProfileAdmin() {
        Profile profile = ProfileBuilder.aProfile()
                .withId(1)
                .withName("ROLE_ADMIN")
                .build();

        adminProfile = profile;
        profileRepository.save(profile);
    }

    private void createProfileUser() {
        Profile profile = ProfileBuilder.aProfile()
                .withId(2)
                .withName("ROLE_USER")
                .build();

        userProfile = profile;
        profileRepository.save(profile);
    }

    private void createAlgorithms() {
        Algorithm algorithm1 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Colaborativa")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm2 = AlgorithmBuilder.anAlgorithm()
                .withId(2)
                .withName("Filtragem Baseada em Conteúdo")
                .withTypeRecommendation(TypeRecommendation.CONTENT)
                .build();

        Algorithm algorithm3 = AlgorithmBuilder.anAlgorithm()
                .withId(3)
                .withName("Filtragem Híbrida Ponderada")
                .withTypeRecommendation(TypeRecommendation.HYBRID)
                .build();

        Algorithm algorithm4 = AlgorithmBuilder.anAlgorithm()
                .withId(4)
                .withName("Filtragem Híbrida Mista")
                .withTypeRecommendation(TypeRecommendation.HYBRID)
                .build();
        Algorithm algorithm5 = AlgorithmBuilder.anAlgorithm()
                .withId(5)
                .withName("Filtragem Híbrida por Combinação Sequencial")
                .withTypeRecommendation(TypeRecommendation.HYBRID)
                .build();


        algorithmRepository.saveAll(Arrays.asList(
                algorithm1, algorithm2,
                algorithm3, algorithm4,
                algorithm5
        ));
    }
}
