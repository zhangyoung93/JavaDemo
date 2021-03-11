package com.zy.demo.algorithm;

/**
 * 动态规划
 * @author zy
 */
public class DynamicProgramming {

    /**
     * 求解指定两个字符串的最大公共子字符串
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最大公共字符串
     */
    public static String getCommonStr(String str1,String str2){
        //字符串分别转字符数组
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        //构建策略集合二维数组(第一行第一列仅用于占位)
        int[][] strategies = new int[c1.length+1][c2.length+1];
        //填充策略集合
        for(int i = 1 ; i <= c1.length ; i++){
            for(int j = 1 ; j <= c2.length ; j++){
                //分阶段、找状态、做决策
                if(c1[i-1] == c2[j-1]){
                    //状态转移(解决某列字符重复相等的问题)
                    strategies[i][j] = strategies[i-1][j-1]+1;
                }
            }
        }
        //定义最大公共字符串长度
        int maxLen = 0;
        //定义索引游标(假设以字符串str1作为参照物)
        int index = 0;
        //遍历策略集合
        for(int i = 0 ; i <= c1.length ; i++){
            for(int j = 0 ; j <= c2.length ; j++){
                if(strategies[i][j] > maxLen){
                    //找最优策略
                    maxLen = strategies[i][j];
                    index = i;
                }
            }
        }
        //最大公共字符串
        StringBuilder commonStr = new StringBuilder();
        //找终止条件(从起始索引开始拼接公共字符串)
        for(int i = index - maxLen ; i < index ; i++){
            commonStr.append(c1[i]);
        }
        return commonStr.toString();
    }
}
