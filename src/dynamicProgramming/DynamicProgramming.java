package dynamicProgramming;

public class DynamicProgramming {
	
	public static int longestCommonSubsequence(String strOne, String strTwo) {
		if(strOne.length() == 0 || strTwo.length() == 0) {
			return 0;
		} else if(strOne.charAt(0) == strTwo.charAt(0)) {
			return longestCommonSubsequence(strOne.substring(1), strTwo.substring(1)) + 1;
		} else {
			int removeStrOne = longestCommonSubsequence(strOne.substring(1), strTwo);
			int removeStrTwo = longestCommonSubsequence(strOne, strTwo.substring(1));
			
			return Math.max(removeStrOne, removeStrTwo);
		}
	}
	
	public static int longestIncreasingSubsequence(int[] array, int lastNumIndex, int index) {
		if(array.length == index) {
			return 0;
		} else {
			int remove = longestIncreasingSubsequence(array, lastNumIndex, index + 1);
			int include = 0;
			if(lastNumIndex == -1 || array[lastNumIndex] < array[index]) {
				include = longestIncreasingSubsequence(array, index, index + 1) + 1;
			}
			return Math.max(remove, include);
		}
	}
	
	public static int editDistance(String strOne, String strTwo, int indexOne, int indexTwo) {
		if(strOne.length() == indexOne) {
			return strTwo.length() - indexTwo;
		} else if(strTwo.length() == indexTwo) {
			return strOne.length() - indexOne;
		} else if(strOne.charAt(indexOne) == strTwo.charAt(indexTwo)) {
			return editDistance(strOne, strTwo, indexOne + 1, indexTwo + 1);
		} else {
			int remove = editDistance(strOne, strTwo, indexOne + 1, indexTwo) + 1;
			int insert = editDistance(strOne, strTwo, indexOne, indexTwo + 1) + 1;
			int replace = editDistance(strOne, strTwo, indexOne + 1, indexTwo + 1) + 1;
			return Math.min(Math.min(remove, insert), replace);
		}
	}

	public static int minPartition(int[] array, int total, int included, int index) {
		if(index == array.length) {
			return Math.abs(total - included - included);
		} else {
			int includedMin = minPartition(array, total, included + array[index], index + 1);
			int notIncludedMin = minPartition(array, total, included, index + 1);
			
			return Math.min(includedMin, notIncludedMin);
		}
	}
	
	public static int coverDistance(int distance) {
		if(distance < 0) {
			return 0;
		} else if(distance == 0) {
			return 1;
		} else {
			return coverDistance(distance - 1) + coverDistance(distance - 2) + coverDistance(distance - 3);
		}
	}
	
	public static boolean subsetSum(int[] array, int sum, int index) {
		if(sum == 0) {
			return true;
		} else if(array.length == index) {
			return false;
		} else if(array[index] > sum) {
			return subsetSum(array, sum, index + 1);
		}
		return subsetSum(array, sum - array[index], index + 1) || subsetSum(array, sum, index + 1);
	}
	
	public static int knapsack(int[] value, int[] weight, int weightLeft, int index) {
		if(value.length == index) {
			return 0;
		} else if(weight[index] > weightLeft) {
			return knapsack(value, weight, weightLeft, index + 1);
		} else {
			int take = knapsack(value, weight, weightLeft - weight[index], index + 1) + value[index];
			int noTake = knapsack(value, weight, weightLeft, index + 1);
			
			return Math.max(take, noTake);
		}
	}
	

	public static void main(String[] args) {
		System.out.println("Longest Common Subsequence");
		System.out.println(longestCommonSubsequence("ABCDGH", "AEDFHR"));
		System.out.println(longestCommonSubsequence("AGGTAB", "GXTXAYB"));
		
		System.out.println("Longest Increasing Subsequence");
		int[] array1 = {3, 10, 2, 1, 20};
		System.out.println(longestIncreasingSubsequence(array1, -1, 0));
		int[] array2 = {3, 2};
		System.out.println(longestIncreasingSubsequence(array2, -1, 0));
		int[] array3 = {50, 3, 10, 7, 40, 80};
		System.out.println(longestIncreasingSubsequence(array3, -1, 0));
		
		System.out.println("Edit Distance");
		System.out.println(editDistance("geek", "gesk", 0, 0));
		System.out.println(editDistance("cat", "cut", 0, 0));
		System.out.println(editDistance("sunday", "saturday", 0, 0));
		System.out.println(editDistance("sundrtyher", "satufadsfr", 0, 0));
		System.out.println(editDistance("sunasdffsdafsdfasdr", "satufadasdfasfsfr", 0, 0));
		
		System.out.println("Minimum Partition");
		int[] array4 = {1, 6, 11, 5};
		System.out.println(minPartition(array4, 23, 0, 0));
		int[] array5 = {3, 10, 2, 1, 20};
		System.out.println(minPartition(array5, 36, 0, 0));
		int[] array6 = {3, 1, 4, 2, 2, 1};
		System.out.println(minPartition(array6, 13, 0, 0));
		int[] array7 = {10, 20, 2, 1, 3};
		System.out.println(minPartition(array7, 36, 0, 0));
		
		System.out.println("Cover Distance");
		System.out.println(coverDistance(3));
		System.out.println(coverDistance(4));
		System.out.println(coverDistance(5));
		
		System.out.println("Subset Sum");
		int[] array8 = {3, 34, 4, 12, 5, 2};
		System.out.println(subsetSum(array8, 9, 0));
		
		System.out.println("Knapsack");
		int[] value1 = {60, 100, 120};
		int[] weight1 = {10, 20, 30};
		System.out.println(knapsack(value1, weight1, 50, 0));
		
		
		
		
		System.out.println("Done");

	}

}
