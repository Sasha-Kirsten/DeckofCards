import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String[] ranks = {"Royal Flush", "Straight Flush", "4-of-a-kind", "Full House", "Flush", "Straight",
            "3-of-a-kind", "2 Pair", "1 Pair", "High Card"};

    public void addCard(Card c){
        if(hand.size() < 5){
            hand.add(c);
        }
    }

    public String getHandRank(){
        if(hand.size() != 5){
            return "Incorrect hand size";
        }
        //sort the hand
        hand.sort(new SortCards());

        //check for each type of hand

        boolean flush = isFlush();

        boolean straight = isStraight();

        if(flush && straight){
            if(getValuesAsInt(hand.get(0))==10){
                return ranks[0];
            }else{
                return ranks[1];
            }
        }

        int[] valueCounts = getValueCounts();

        int maxCount = getMaxCount(valueCounts);

        if(maxCount == 4){
            return ranks[2];
        }else if(maxCount==3 && hasPair(valueCounts)){
            return ranks[3];
        }else if(flush){
            return ranks[4]; // Flush
        } else if (straight) {
            return ranks[5]; // Straight
        } else if (maxCount == 3) {
            return ranks[6]; // 3-of-a-kind
        } else if (maxCount == 2 && numPairs(valueCounts) == 2) {
            return ranks[7]; // 2 Pair
        } else if (maxCount == 2) {
            return ranks[8]; // 1 Pair
        } else {
            return ranks[9]; // High Card
        }
    }

    private int getValuesAsInt(Card card){
        String value = card.getValue();
        switch (value){
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

        private boolean isFlush(){
            String suit = hand.get(0).getSuit();
            for(Card c: hand){
                if(!c.getSuit().equals(suit)){
                    return false;
                }
            }
            return true;
        }

        private boolean isStraight(){
            for(int i=0;i<hand.size()-1;i++){
                if(hand.get(i).getValue()+1 != hand.get(i+1).getValue()){
                    return false;
                }
            }
            return true;
        }

        private int[] getValueCounts(){
            int[] counts = new int[15];
            for(Card c:hand){
                counts[getValuesAsInt(c)]++;
            }
            return counts;
        }

        private int getMaxCount(int[] counts){
            int max = 0;
            for(int count:counts){
                if(count>max){
                    max=count;
                }
            }
            return max;
        }

        private boolean hasPair(int[] counts){
            for(int count:counts){
                if(count==2){
                    return true;
                }
            }
            return false;
        }

        private int numPairs(int[] counts){
            int pairs = 0;
            for(int count:counts){
                if(count ==2){
                    pairs++;
                }
            }
            return pairs;
        }


        //high card (None of the other hands match, the highest value of the card)

        //one pair ( a pair of cards with the same value e.g. 7D, 7H, 4S, 6H, 8H)

        //two pair (2 pairs of matched values e.g. 7D, 7H, 4S, 4C, 2D)

        //3 of a kind (3 cards with the same value and two others e.g. 7D, 7H, 7C, 2H, KS)

        //straight (A run of values in different suits e.g. 3H, 4D, 5H, 6C, 7S)

        //flush (All cards are in the same suit e.g. 3H, 7H, 9H, JH, KH)

        //full house (3 of a kind and a pair e.g. 7S, 7H, 7D, 4C, 4H)

        //4 of a kind (4 cards with the same value e.g. 9S, 9C, 9H, 9D, 7D)

        //straight flush (5 cards in a row all of the same suit e.g. 3S, 4S, 5S, 6S, 7S)

        //royal flush (J,Q,K,A,10 all of the same suit)


    public String toString(){
        String output = "";
        for(Card c: hand){
            if(c.getSuit().equals("Hearts") || c.getSuit().equals("Diamonds")) {
                output += "\u001B[31m[ " + c.getValue() + " , " + c.getSuit() + " ] \u001B[0m";
            }
            else{
                output += "[ " + c.getValue() + " , " + c.getSuit() + " ] ";
            }
        }

        return output;
    }
}
