import java.util.*;

/*
1. 어떤 순서로 일어났는지 기록: userid
2. Enter인지 Leave인지 순서기록 
3. userid : nickname 맵핑하는 Hashmap 

case Enter , Change, Leave 나눠서 분기 처리 

if(Enter) -> list에 Enter 넣고 Map에 userid:nickname 추가 
*/

class Solution {
    public String[] solution(String[] record) {
        
        Map<String,String> map = new HashMap<>(); //userid : nickname
        List<String> list1 = new ArrayList<>(); //userid 순서대로
        List<Integer> list2 = new ArrayList<>(); //Enter : 0, Leave : 1  
        
        for(String message : record){
            String [] input = message.split(" ");
            
            if(input[0].equals("Enter")){
                map.put(input[1],input[2]);
                list1.add(input[1]);
                list2.add(0);
            }
            
            else if(input[0].equals("Leave")){
                list1.add(input[1]);
                list2.add(1);
            }
            
            //change id 
            else{
                map.put(input[1],input[2]);
            }
            
        }
            
        String [] answer = new String[list1.size()];
        
        for(int i = 0; i < list1.size(); i++){
            //Enter 처리
            if(list2.get(i)==0){
                answer[i] = map.get(list1.get(i))+"님이 들어왔습니다.";
            }
                
            //Leave 처리
            else{
                 answer[i] = map.get(list1.get(i))+"님이 나갔습니다.";   
            }
                
        }
            
        return answer;
    }
}