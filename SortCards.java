import java.util.Comparator;

public class SortCards implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        int card1 = convertCardValueToInt(o1.getValue());
        int card2 = convertCardValueToInt(o2.getValue());

        if(o1.getValue() == "J" || o1.getValue() == "A" || o1.getValue() == "Q" || o1.getValue() == "K"){
            card1 = o1.getNumericValue();
        }else{
            card1 = Integer.parseInt(o1.getValue());
        }

        if(o2.getValue() == "J" || o2.getValue() == "A" || o2.getValue() == "Q" || o2.getValue() == "K"){
            card2 = o2.getNumericValue();
        }else{
            card2 = Integer.parseInt(o2.getValue());
        }

        if(card1 > card2){
            return 1;
        }else if(card1 < card2){
            return -1;
        }else{
            return 0;
        }
    }

        private int convertCardValueToInt(String value){
            switch(value){
                case "A":
                    return 14;
                case "K":
                    return 13;
                case "Q":
                    return 12;
                case "J":
                    return 11;
                default:
                    return Integer.parseInt(value);
            }
        }
}
