/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:FileUtilsTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

/**
 * Created by zpy on 2018/8/24.
 */
public class FileUtilsTest {
    String fileName = "E:\\projects\\Yearn\\src\\test\\resources\\FileTest.md";
/*
    @Test
    public void testGetContent() throws IOException, YearnException {
        String res = FileUtils.getContent(fileName);
        Assert.assertTrue(res.contains("African American drama has, until recently, been rooted in the mimetic tradition of modern"));
        Assert.assertTrue(res.contains("直到最近，非洲裔美国戏剧一直根植于现代美国自然主义的模仿传统。"));
        Assert.assertTrue(res.contains("# 翻译文章"));
        *//*Assert.assertEquals(
                res,
                "# 原文\n" +
                        "African American drama has, until recently, been rooted in the mimetic tradition of modern\n" +
                        "American naturalism. The most distinctive attribute of this tradition is the mechanistic, materialistic\n" +
                        "conception of humanity.\n" +
                        "\n" +
                        "# 翻译文章\n" +
                        "直到最近，非洲裔美国戏剧一直根植于现代美国自然主义的模仿传统。这种传统最鲜明的特点是人性的机械性、唯物主义概念。自然主义认为每个个体逃不掉环境的边界，并把每个人描绘成一种人———他被具体的现实所控制，而不是去控制现实。只要非洲裔美国戏剧维护自然主义作为它的主要模式，它就只能表现非洲裔美国人的困境。它的英雄们可能会宣称现实的疯狂，但现实不可避免地战胜了他们。\n" +
                        "AK的超现实主义戏剧标志了一个作为第一批从自然主义逃离的非洲裔美国戏剧家。在他的框架内，AK已经能够描绘非洲裔美国人的思想和灵魂从外部环境的联系中被解放了出来。\n" +
                        "\n" +
                        "# 文章结构\n" +
                        "直到最近，一直\n" +
                        "\n" +
                        "非洲裔美国戏剧\n" +
                        "    表现非洲裔美国人的困境\n" +
                        "        它就只能\n" +
                        "        只要非洲裔美国戏剧维护自然主义作为它的主要模式\n" +
                        "    它的英雄们\n" +
                        "        宣称现实的疯狂，\n" +
                        "        但现实不可避免地战胜了他们。\n" +
                        "\n" +
                        "起因于（根植）\n" +
                        "\n" +
                        "模仿传统\n" +
                        "    最鲜明的特点：人性的机械性、唯物主义概念。\n" +
                        "    现代美国自然主义\n" +
                        "            认为每个个体逃不掉环境的边界，并把每个人描绘成一种人———他被具体的现实所控制，而不是去控制现实\n" +
                        "\n" +
                        "# Q1\n" +
                        "哪个最好地陈述了文章的中心思想？\n" +
                        "A. 非洲裔美国戏剧一直主要被强调唯物主义的自然主义影响。\n" +
                        "Solution:\n" +
                        "第一段\n" +
                        "\n" +
                        "B. 非洲裔美国戏剧一直传统地承认个人和环境的关系。\n" +
                        "C. 传统自然主义的非洲裔美国戏剧基本没有被AK的精神和对戏剧的心理方式影响。\n" +
                        "D. AK的作品提议非洲裔美国戏剧从一种奉行严格的自然主义的转换。\n" +
                        "E. AK的作品最好地例证了在精神和心理学范畴内现在非洲裔美国艺术家的兴趣。\n".trim());*//*
    }

    @Test
    public void testGetLines() throws IOException, YearnException {
        List<String> fileLines = FileUtils.getLines(fileName);
        Assert.assertEquals(fileLines.get(0),"# 原文\n".trim());
        Assert.assertEquals(37,fileLines.size());
    }*/
}
