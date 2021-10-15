/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ChatbotTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade;

import com.zpy.yearn.service.ChSentenceService;

import javax.annotation.Resource;

/**
 * Created by zpy on 2018/9/1.
 */
public class ChatbotTest {//extends TestBase {
    @Resource
    private Chatbot chatbot;
    @Resource
    private ChSentenceService chSentenceService;

    /*@Test
    public void recognizeConcept() {

        *//*Tree defTree = chSentenceService.explainOnce("非此即彼的思想是指你倾向于用一种极端的、黑\n" + "白分明的标准来评价你自己。").getTree();


        List<Definition> defs = new ArrayList<>();
        defs.add(new Definition(defTree));

        Concept concept = new Concept("非此即彼思想", defs);

        chatbot.acceptConcept(concept);

        chatbot.setMode(BotMode.PSYCHO_COUNSEL);
        Tree tree = chatbot.heardInPsychoCounsel("我输掉了州长竞选，所以我现在一无是处").getTree().getTree();
        String thoughts = chatbot.sayThoughts();*//*
    }

    @Test
    public void showTree() {
        //Tree tree = chatbot.heardInPsychoCounsel("你愚蠢地用一种极端的、黑\n" +
        //          "白分明的标准来评价你自己。");
        *//*Tree tree = chatbot.parseTree("你用一种极端的标准来评价你自己。").get(0);
        tree.pennPrint();

        Tree testParentSib = tree.children()[0].children()[0];
        Tree parent = testParentSib.parent(tree);
        print("parent:");
        parent.pennPrint();
        print("siblings:");
        testParentSib.siblings(tree).get(0).pennPrint();

        print("constituents: ");//树里面的边
        Set<Constituent> constituents = tree.constituents();
        for (Constituent constituent : constituents) {
            print("constituent");//Constituent的value(),label(),labels()，score()都没有结果或报错
            print(constituent);
            print("toSentenceString");
            print(constituent.toSentenceString(tree.yield()));
            print("size");
            print(constituent.size());
        }
        print("yield:");//展平的一句话
        print(tree.yield());
            *//**//*try{
                    print("dependencies:");
                    print(tree.dependencies());//报错
            }catch (Exception e){
                print(ExceptionUtils.getStackTrace(e));
            }*//**//*
        print("flatten:");
        print(tree.flatten());
        print("get leaves:");
        print(tree.leaves());*//*
    }*/
/*
    @Test
    public void testHeard1() throws YearnException {
        Book book = new Book("伯恩斯新情绪疗法");
        Author author = new Author("David D. Burns");
        book.setAuthor(author);

        List<BookPart> parts = new ArrayList<>();
        BookPart part1 = new BookPart("1.理论和研究");
        parts.add(part1);
        book.setParts(parts);

        List<BookPart> chaps = new ArrayList<>();
        BookPart chap3 = new BookPart("3.破解情绪：思维决定情绪");
        chaps.add(chap3);
        part1.setParts(chaps);

        List<BookPart> secs = new ArrayList<>();
        BookPart sec1 = new BookPart("10大认知扭曲", "非此即彼的思想是指你用一种极端的\n" +
                "标准来评价你自己。");
        *//*BookPart sec1 = new BookPart("10大认知扭曲", "非此即彼的思想是指你倾向于用一种极端的、黑\n" +
                "白分明的标准来评价你自己。");*//*
        secs.add(sec1);
        chap3.setParts(secs);

        chatbot.readBook(book);

        //chatbot.heardInPsychoCounsel("我输掉了州长竞选，所以我现在一无是处");
        chatbot.heardInPsychoCounsel("Tom","我一无是处");
        //bot say 这属于非此即彼

    }*/
}
