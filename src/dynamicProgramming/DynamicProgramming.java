package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

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
	
	public static LinkedList<String> reverseLL(LinkedList<String> current) {
		
		if(current.size() == 1) {
			LinkedList<String> head = new LinkedList<String>();
			head.add(current.pop());
			return head;
		}
		
		String temp = current.pop();
		LinkedList<String> head = reverseLL(current);
		head.add(temp);
		
		return head;
	}
	
	public static ArrayList<ArrayList<Integer>> pairSum(ArrayList<Integer> numbers, int goal) {
		
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < numbers.size() - 1; i++) {
			for(int j = i + 1; j < numbers.size(); j++) {
				if(numbers.get(i) + numbers.get(j) == goal) {
					ArrayList<Integer> combination = new ArrayList<Integer>();
					
					combination.add(numbers.get(i));
					combination.add(numbers.get(j));
					
					combinations.add(combination);
				}
			}
		}
		
		return combinations;
	}
	
	public static boolean balancedParentheses(String string) {
		
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0; i < string.length(); i++) {
			char current = string.charAt(i);
			
			if("{([".indexOf(current) >= 0) {
				stack.push(current);
			} else {
				if(stack.empty()) {
					return false;
				}
				char pop = stack.pop();
				if(!((pop == '{' && current == '}') || (pop == '[' && current == ']') || (pop == '(' && current == ')'))) {
					return false;
				}
			}
		}
		
		if(stack.empty()) {
			return true;
		}
		return false;
	}
	
	public static int[] stockSpan(int[] stockPrices) {
		int[] span = new int[stockPrices.length];
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int count = 1;
		
		for(int i = 0; i < stockPrices.length; i++) {
			if(stack.empty()) {
				stack.push(stockPrices[i]);
				span[i] = 1;
			}
			
			int current = stockPrices[i];
			int top = stack.peek();
			
			if(top >= current) {
				stack.push(current);
				span[i] = 1;
			} else if(top < current) {
				
				while(top < current) {
					stack.pop();
					if(stack.empty()) {
						break;
					}
					top = stack.peek();
					
					count++;
				}
				
				if(stack.empty()) {
					count = 1;
				}
				
				stack.push(current);
				span[i] = count;
			}

			
			
		}
		
		return span;
	}
	
	public static boolean jobScheduling(int[] start, int[] end) {
		Arrays.sort(start);
		Arrays.sort(end);
		
		int i = 0;
		int j = 0;
		int jobs = 1;
		
		while(j < end.length) {
			while(j < end.length && start[i] >= end[j]) {
				j++;
				jobs--;
			}
			
			if(jobs > 2) {
				return false;
			} else if(i == start.length - 1) {
				break;
			}
			
			i++;
			jobs++;
		}
		
		return true;
	}
	
	public static int knapsack2(int[] value, int[] weight, int maxWeight, int i) {
		if(i == -1) {
		 return 0;
		} else if(weight[i] > maxWeight) {
			return knapsack(value, weight, maxWeight, i - 1);
		} else {
			int addItem = knapsack(value, weight, maxWeight - weight[i], i--) + value[i];
			int skip = knapsack(value, weight, maxWeight, i--);
			
			return Math.max(addItem, skip);
		}
	}
	

	public static void main(String[] args) {
		System.out.println("Longest Common Subsequence");
		System.out.println(longestCommonSubsequence("ABCDGH", "AEDFHR"));
		System.out.println(longestCommonSubsequence("AGGTAB", "GXTXAYB"));
		
//		System.out.println("Longest Increasing Subsequence");
//		int[] array1 = {3, 10, 2, 1, 20};
//		System.out.println(longestIncreasingSubsequence(array1, -1, 0));
//		int[] array2 = {3, 2};
//		System.out.println(longestIncreasingSubsequence(array2, -1, 0));
//		int[] array3 = {50, 3, 10, 7, 40, 80};
//		System.out.println(longestIncreasingSubsequence(array3, -1, 0));
//		
//		System.out.println("Edit Distance");
//		System.out.println(editDistance("geek", "gesk", 0, 0));
//		System.out.println(editDistance("cat", "cut", 0, 0));
//		System.out.println(editDistance("sunday", "saturday", 0, 0));
//		System.out.println(editDistance("sundrtyher", "satufadsfr", 0, 0));
//		System.out.println(editDistance("sunasdffsdafsdfasdr", "satufadasdfasfsfr", 0, 0));
//		
//		System.out.println("Minimum Partition");
//		int[] array4 = {1, 6, 11, 5};
//		System.out.println(minPartition(array4, 23, 0, 0));
//		int[] array5 = {3, 10, 2, 1, 20};
//		System.out.println(minPartition(array5, 36, 0, 0));
//		int[] array6 = {3, 1, 4, 2, 2, 1};
//		System.out.println(minPartition(array6, 13, 0, 0));
//		int[] array7 = {10, 20, 2, 1, 3};
//		System.out.println(minPartition(array7, 36, 0, 0));
//		
//		System.out.println("Cover Distance");
//		System.out.println(coverDistance(3));
//		System.out.println(coverDistance(4));
//		System.out.println(coverDistance(5));
//		
//		System.out.println("Subset Sum");
//		int[] array8 = {3, 34, 4, 12, 5, 2};
//		System.out.println(subsetSum(array8, 9, 0));
//		
//		System.out.println("Knapsack");
//		int[] value1 = {60, 100, 120};
//		int[] weight1 = {10, 20, 30};
//		System.out.println(knapsack(value1, weight1, 50, 0));
		
//		LinkedList<String> test = new LinkedList<String>();
//		test.add("a");
//		test.add("b");
//		test.add("b");
//		test.add("c");
//		test.add("d");
//		
//		System.out.println(Arrays.toString(test.toArray()));
//		System.out.println(Arrays.toString(reverseLL(test).toArray()));
//		
		
//		ArrayList<Integer> test2 = new ArrayList<Integer>();
//		test2.add(3);
//		test2.add(4);
//		test2.add(5);
//		test2.add(6);
//		test2.add(7);
//		
//		ArrayList<ArrayList<Integer>> result = pairSum(test2, 10);
//		
//		for(int i = 0; i < result.size(); i++) {
//			ArrayList<Integer> current = result.get(i);
//			System.out.println(current.get(0) + ", " + current.get(1));
//		}
//		
//		System.out.println(balancedParentheses("[]"));
//		System.out.println(balancedParentheses("[])"));
//		System.out.println(balancedParentheses("[{[])"));
//		System.out.println(balancedParentheses("[()]{}{[()()]()}"));
//		
//		System.out.println(Arrays.toString(stockSpan(new int[] {100, 75, 85, 120, 130, 1, 1, 1, 2})));
//		System.out.println(Arrays.toString(stockSpan(new int[] {100, 80, 60, 70, 60, 75, 85, 120})));
//		
//		System.out.println(jobScheduling(new int[] {1, 2, 4}, new int[] {2, 3, 5}));
//		System.out.println(jobScheduling(new int[] {1, 2, 2, 1}, new int[] {5, 4, 6, 7}));
//		
//		System.out.println(knapsack2(new int[] {60, 100, 120}, new int[] {10, 20, 30}, 50, 2));
//		System.out.println(knapsack2(new int[] {60, 100, 120, 30, 110}, new int[] {10, 20, 30, 40, 10}, 50, 2));
		
		System.out.println("Done");

	}

}
