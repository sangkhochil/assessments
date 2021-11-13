

/*
	divide this chain into three smaller chains
	
	An array A consisting of N integers is given. The elements of array A together represent a chain, 
	and each element represents the strength of each link in the chain. We want to divide this chain into 
	three smaller chains. All we can do is to break the chain in exactly two non-adjacent positions. 
	More precisely, we should break links P, Q (O < P < Q < N — 1, Q — P > 1), resulting in 
	three chains [O, P - 1], [P + 1, Q - 1] [Q + 1, N - 1]. The total cost of this operation is equal to A[P] + A[Q]
	For example, consider array A such that: { 5, 2, 4, 6, 3, 7 }
	We can choose to break the following links:
	• 1,3 : total cost is 2+6=8
	• 1,4 :total cost is 2+3=5
	• 2,4: total cost is 4+3=7
*/


class Solution3 {
 public int solution(int[] A) {
     //if(A.length < 5) return -1;
     //int min = Integer.MAX_VALUE;

     // for(int i = 1; i < A.length - 3; i++) {
     //     for(int j = i+2; j < A.length - 1; j++) {
     //         int tmp = A[i] + A[j];
     //         if(min > tmp) min = tmp;
     //         //System.out.println(tmp);
     //     }
     // }

     int M = A[1];
     int P = A[2];
     int Sum = A[1] + A[3];
     for(int i = 4; i < A.length - 1; i++) {
         M = Math.min(M,P);
         Sum = Math.min(Sum, (M + A[i]));
         P = A[i-1];
     }
     
     return Sum;
 }
}
