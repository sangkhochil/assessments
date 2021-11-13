// you can also use imports, for example:
import java.util.*;

/*
 	Minimum characters required to be removed to make frequency of each character unique
	Difficulty Level : Easy
	Last Updated : 24 Jun, 2021
	Given string str, the task is to find the minimum count of characters that need to be deleted from the string such that the frequency of each character of the string is unique.
	
	Examples:
	
	Input: str = “ceabaacb” 
	Output: 2 
	Explanation: 
	The frequencies of each distinct character are as follows: 
	c —> 2 
	e —> 1 
	a —> 3 
	b —> 2 
	Possible ways to make frequency of each character unique by minimum number of moves are: 
	
	Removing both occurrences of ‘c’ modifies str to “eabaab”
	Removing an occurrence of ‘c’ and ‘e’ modifies str to “abaacb”
	Therefore, the minimum removals required is 2.
	
	Input: S = “abbbcccd” 
	Output: 2 
 */

class Solution2 {
    public int solution(String S) {
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, 01);
			}
		});

        char[] tmp = S.toCharArray();

        for(int i = 0; i < S.length(); i++) {
            if(map.containsKey(tmp[i]))
                map.put(tmp[i], map.get(tmp[i]) + 1);
            else
                map.put(tmp[i], 1);            
        }

        for( Map.Entry<Character, Integer> entry : map.entrySet()) {
            //System.out.println("Value = " + (int)entry.getValue());
            pq.add(entry.getValue());
        }

        while(!pq.isEmpty()) {
            int freq = pq.remove();
            if(pq.isEmpty()) return count;

            if(freq == pq.peek()) {
                if(freq > 1) pq.add(freq -1);
                
                count++;
            }
        }

        return count;
    }
}

