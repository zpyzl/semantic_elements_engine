/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:GREPassagesDemo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.gre

import com.zpy.yearn.facade.Chatbot

/**
  * Created by zpy on 2019/8/28.
  */
object GREPassagesDemo {
/*
Passage 17（短文章／观点论证／动物／易）
Early naturalists believed two species of beaver lived in North America: dam beavers and bank
beavers. The bank species was thought to resemble the muskrat in behavior, living in burrows or
lodges and unable to build dams. In fact, dams are primarily a strategy for dealing with annual
variations in water levels. If water levels fall in summer, as they do in most of North America, then
beavers lodge entrances may be exposed. With stabilized water levels, their homes are much
safer. Along deep rivers, where bank beavers are found, this problem seldom arises. But these
beavers do know how to build dams, and do so if the need arises, as may occur if they are forced
to relocate after felling and consuming all nearby trees.
1. The passage provides support for which of the following statements about beaver dams?
A. One important function of these dams is to protect beavers homes.
B. Most are built prior to burrow construction.
C. They are found mostly along deep rivers.
D. They are routinely abandoned as nearby forests are depleted.
E. They mainly protect beavers from rising water levels.
2. The passage implies which of the following about beavers?
A. Bank beavers are unable to successfully compete with dam beavers when resources become
scarce.
B. Differences in dam-building behavior among beavers do not necessarily imply multiple beaver
species.
C. Building dams eventually causes beavers to deplete nearby resources.
D. When conditions permit, beavers are more likely to build dams than burrows or lodges.
E. In beavers, dam-building is an acquired rather than an innate skill

Q1:
答案A
A:水坝的一个重要的功能是保护海狸的家
水坝是为了应对水位变化
水位稳定家就安全=》水位不稳定家就不安全
水位是为了应对水位变化导致的家的不安全

Q2: 答案B
B：筑坝行为的不同=》不筑坝的岸海狸和筑坝的岸海狸，但是同一种海狸
如果需求出现，岸海狸就筑坝·


 */
  def p17q2(chatbot: Chatbot): Boolean = {
   // chatbot.judge("GREPassage17","")
    true
  }

/*
Passage 28（短文章／问题解决／社会／易）
Early life insurers in the United States found themselves facing the problem of obtaining reliable
information, as they needed to rely on applicants themselves to provide truthful, complete answers
to a standard set of questions. In an attempt to personalize the relationship between insurers and
their individual applicants, firms selected highly respected local citizens to act as their agents.
These agents were expected to evaluate the appearance of candidates, unearth evidence of
unhealthy family histories or questionable habits, and attest to the respectability of the people
writing testimonial letters on an applicant's behalf, In short, the initial purpose of the agency system
was not to actively solicit customers, but, rather, to recreate the glass-bowl mentality associated
with small towns or city neighborhoods.
各句大意概括：
保险公司面临申请者的可信问题。 (起因）
保险公司为了了解每个申请者的情况，请了当地公民作为代理去了解。（起因+做法）
代理们要评估。。（做法内容）
代理制度的目标

1. The primary purpose of the passage is to 答案A
A. explain the original function of life insurance agents
为了实现判断，需要证明：function of life insurance agents = purpose of the agency system（原文中），
即证明agency system的目的 = 代理的目的
词语解释：
（非人的）目标：其作用、功能   ==多义词==
体系：若干事物形成的整体

最后一句话agency system翻译成代理体系：代理组成的整体
则“代理组成的整体的目的” 和 “整体的目的就是部分的目的”（常识）
推导出=》  代理的目的

注：“整体的目的就是部分的目的”，整体的目的可能并不是部分的直接目的，而是是部分的间接目的。比如汽车的目的是运送人，但汽车雨刷的直接目的不是运送人，间接目的是运送人（雨刷让司机雨天可以驾驶，从而让雨天可运送人）


agency system如果翻译成代理制度：
代理制度的目的=》代理行为的规则的目的，是约束代理行为，使代理行为达到其目的
规则 cause （代理行为 cause 代理目的）& (A cause (B cause C) => A cause C )
=》规则 cause 代理目的

B. evaluate the effectiveness of early life insurance agents
有效：达到效果、目的。agents were expected to，期待，并不是实际情况，没有一般过去时表示代理造成怎样效果，所以错误

C. describe how life insurance was first introduced
D. illustrate how the life insurance agency system changed over time
E. compare the strategies used by life insurance in cities and in small towns

2. The author suggests which of the following about “city neighborhoods”? 答案D
A. They were places where family histories were difficult to establish.
B. They were places where unhealthy behaviors had been successfully addressed.
C. They were locations that were well suited for recruiting insurance agents.
D. They offered a high degree of transparency about a resident's personal history and character.
提供了吗？文章没有说实际情况，有点武断。
E. They offered potentially fruitful markets for the life insurance industry


Passage 15（短文章／艺术评论／中等）
David Belasco’s 1912 Broadway production of The Governors Lady created a sensation with a
scene set in a Childs cafeteria, a chain restaurant that was an innovator in food standardization and emblematic of modern everyday life.
(2) While Belasco’s meticulously detailed reproduction of an immediately recognizable setting impressed the public, it was derided by progressive theater critics
who championed the New Stagecraft theories of European artists like Max Reinhardt.
贝拉的谨慎细致的、对一种吸引眼球的布置的复制 给大众留下了印象， 它被那些。。的进步戏剧评论家嘲笑。

 The New Stagecraft rejected theatrical literalism; it drew inspiration from the subjectivity and minimalism of
modern painters, advocating simplified sets designed to express a dramatic texts central ideas.
新时代拒绝写实；它从现代画家的主观主义和最小主义中获取灵感，倡导 为了表现中心思想 的简化布置
Such critics considered Belasco a craftsman who merely captured surface realities: a true artist
eliminated the inessential to create more meaningful, expressive stage images.
评论家认为贝拉是这样一种匠人：仅仅捕捉表面实情。一个真正的艺术家去除不必要的，目的是创造更有意义、表现力强的图画

1. The author of the passage implies which of the following about Belasco’s production of The
Governors Lady?  答案：D
A. It was dismissed by certain theater critics who misunderstood Belasco’s conception of
modernity.
B. It was intended to marshal elements of the New Stagecraft to serve Belasco’s predilection for
realistic staging.
C. It demonstrated that theatrical literalism could be used effectively to express a dramatic texts
central ideas.
D. It elicited responses that reflected a discrepancy between popular tastes in entertainment and the tastes of progressive theater critics.

解释：
D. 它引出一些评价，这些评价 反映了 大众娱乐品位和先进戏剧评论家的品位 的不一致
(2): 贝拉的复制 被大众钦佩， 被进步评论家嘲笑 #1
钦佩：认为好   嘲笑：认为不好，可能其自身却不知道
品味：什么是好，什么是不好的标准
大众认为好，先进评论家认为不好，所以标准不一致
=》 （评论家认为不好=》标准不一致）
反映：推理出

贝拉的产品（制作）  贝拉复制（制作） ，都是制作，说明是同一个行为
=》 贝拉的产品就是贝拉的复制 & #1
贝拉的产品 评论家认为不好 & （认为某事=》某事是认为的原因）
=》 因：贝拉的产品 果：评论家认为不好
=》 贝拉的产品引起 评论家认为不好

E. It relied on the appeal of an impressively realistic stage set to compensate for weaknesses in
other aspects of the production.
2. It can be inferred that the theater critics would be most likely to agree with which of the following
statements about the theatrical productions? 答案B
A. Theatrical productions that seek to eliminate the inessential also often eliminate theatrical
elements that enhance the expressiveness of a play.
B. Theatrical productions that faithfully recreate the visual details of everyday life are unlikely to do
justice to a good play’s central ideas.
C. Theatrical productions that employ the minimalism characteristic of modern paintings may have
greater appeal to modern audiences than productions that rely on theatrical literalism.
D. Theatrical productions that aim to represent truths about modern life should not attempt to
employ elements of the New Stagecraft.
E. Theatrical productions that attempt to produce authentic-looking scenes of everyday reality are
likely to fail in that goal because of the theaters inherent limitations.
解释：
B: 如实重建日常生活视觉细节的产品，不太可能表现中心思想

原文：
贝拉的谨慎细致的、对一种吸引眼球的布置的复制 被大众钦佩， 它被那些拥护新时代理论的进步戏剧评论家嘲笑。
新时代拒绝写实；它从现代画家的主观主义和最小主义中获取灵感，倡导 其设计为了表现中心思想 的简化布置
评论家认为贝拉是这样一种匠人：仅仅捕捉表面实情。一个真正的艺术家去除不必要的，目的是创造更有意义、表现力强的图画

证明：评论家认为表现细节的贝拉不太可能表现中心思想（推4），则原题可证

评论家嘲笑贝拉的复制 & 嘲笑：认为不正确
=》评论家认为贝拉的复制不正确
& 张三认为P1是不正确的=》P1不符合张三的某想法 常识3
=》贝拉的复制不符合评论家的某想法 推5

证明：布置要简单要为了表现中心思想是评论家的一个想法 推6

猜测：(V a uncertain B, C is a B =guess> V C ) 推5 & 推6
=》 贝拉的复制不符合评论家的布置要简单、要表现中心思想的想法
则推4可证

评论家拥护新时代主义 & 拥护：认为正确
=》 评论家认为新时代正确 推1
新时代倡导简化、为了中心思想的布置正确 & 倡导：认为某事正确且希望别人也这样
=》 新时代认为简化、为了中心思想的布置正确 推2
 & （思想认为=》思想包括 常识1）
=》 新时代主义包括 简化、为了中心思想的布置 正确
 & 推1：评论家认为 新时代正确 & 整体正确则部分正确 常识2
=》 评论家认为 简化、为了中心思想的布置正确 推3
& 定语A+中心语B = B是A  #句式转换1
=》 评论家认为 布置是为了中心思想 是正确的
& （A是B）是正确的=A应该是B #正确-应该转换
=》 评论家认为 布置应该是为了中心思想
& #认为-想法转换
=》 布置应该是为了中心思想 是评论家的一个想法
推6得证




=》 评论家认为 布置应该是简化的、布置是应该为了中心思想

& 简化布置是为了表现中心思想 是 简化布置的一个性质 & 常识4：认为某事正确则其性质正确
=》 评论家认为 简化布置是为了表现中心思想 推7
=》 评论家认为 布置

& 推3：评论家认为 简化布置正确 &

评论家认为 以表现中心思想为目的的 简化布置 是正确的

抽象出，设计是为了表现中心思想


有意义=表现中心思想

注：整体V则部分V 不一定正确。比如整体的目的只是部分的间接目的。又如整体能成功，部分不一定成功。故很多词解释的时候，要解释其对部分的适用性。

 */

/*
For years, the leading theory for what caused the Younger Dryas (a dramatic reversal, about
12,900 years ago, in a global warming trend) was a release of water from Glacial Lake Agassiz.
很多年来，YD（1万多年前全球变暖趋势中的一个戏剧性反转）产生原因的最先进理论是来自GLA的水的放出。
The theory posited that this meltwater flooded into the North Atlantic, lowering the salinity and
intensity of surface waters enough to prevent them from sinking.

 Ocean currents were changed in
such a way that northward transport of heat in the ocean diminished, and the North Atlantic regions
plunged back into near-glacial conditions. However, evidence has emerged that the Younger Dryas
began long before freshwater flooded the North Atlantic. Additionally, the temperature changes
induced by a shutdown in the North Atlantic heat conveyor system are too small to explain the
Younger Dryas.

1. The author of the passage implies which of the following about the release of water from glacial
Lake Agassiz?
A. The notion that the release occurred has been challenged by more recent findings.
B. The release probably occurred much earlier than scientists have generally assumed.
C. The release would not have been sufficient to cause any temperature change in the North
Atlantic.
D. The timing of the release is such that it probably did not trigger the onset of the Younger Dryas.
E. The release was probably unrelated to the global warming trend that was taking place.
2. The passage is primarily concerned with
A. presenting evidence that undermines an explanation
B. explaining the nature of a climatological phenomenon
C. questioning the timing of a particular event
D. discussing a new explanation for a phenomenon
E. suggesting revisions to a popular theory


 */

}
