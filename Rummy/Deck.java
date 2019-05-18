import java.util.ArrayList;
import java.util.Collections;

public class Deck{

	//instance variables
	private int numOfRanks;
	private int size;
	private ArrayList<Card> deck;

	//constructors
	public Deck(){
		deck = new ArrayList<Card>();
	}

	public Deck(int range) {
        this(); 

        for (int suit=0; suit<4; suit++) {
            for (int rank=1; rank<=range; rank++) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    //returns size of deck
	public int size(){
		return deck.size();
	}

	//checks if deck has cards
	public boolean hasCards(){
		return !deck.isEmpty();
	}

	//gets the card at the position 'pos' in the deck
	public Card get(int pos){
		return deck.get(pos);
	}

	//adds 'card' to the deck
	public void add(Card card){
		deck.add(card);
		size++;
	
	}

	//adds all of the cards in 'other' to the deck
	public void addAll(Deck other){
		for(int i =0;i<other.size();i++){
			deck.add(other.get(i));
			other.remove(other.get(i));
			size++;
		}
	}

	//removes the last card in the deck
	public Card removeLast(){
		Card temp = deck.get(size-1);
		deck.remove(size-1);
		size--;
		return temp;
	}

	//removes the first card in the deck
	public Card removeFirst(){
		Card temp = deck.get(0);
		deck.remove(0);
		size--;
		return temp;
	}

	//returns if 'card' is in the deck and removes it if it is
	public boolean remove(Card card){
		if(deck.contains(card)){
			deck.remove(card);
			size--;
			return true;
		}

		return false;
	}

	//removes all the cards in 'other' from the deck
	public void removeAll(Deck other){
		for(int i=0;i<other.size();i++){

			if(deck.contains(other.get(i))){
				deck.remove(other.get(i));
				size--;
			}
		}
	}

	//shuffles the deck
	public void shuffle(){
		Collections.shuffle(deck);
	}

	//deals 'n' amount of cards
	public Deck deal(int n){
		Deck newDeck = new Deck();

		for(int i = size-1;i>size-(n-1);i--){
			newDeck.add(deck.get(i));
			deck.remove(i);
			size--;
		}

		return newDeck;
	}

	//returns true if 'card' is in the deck
	public boolean contains(Card card){
		return deck.contains(card);	

	}

	//returns true if deck contains all the cards in 'other'
	public boolean containsAll(Deck other){
		for(int i =0;i<other.size();i++){

			if(!deck.contains(other.get(i))){
				return false;
			}
		}

		return true;
	}

	//checks if the deck 'isKind' (the deck has atleast 2 cards and has all the same ranks)
	public boolean isKind(){
        if (deck.size() < 2) {
            return false;
        }

        for (int i=1; i<deck.size(); i++) {
            if (deck.get(i-1).getRank() != deck.get(i).getRank()) {
                return false;
            }
        }

        return true;
    }

    //checks if the deck 'isSeq' (the deck has atleast 3 cards that are all the same suits and consecutive ranks)
	public boolean isSeq(){
        if (deck.size() < 3) {
            return false;
        }
        sortByRank();

        for (int i=1; i<deck.size(); i++) {

            Card a, b;

            a = deck.get(i-1);
            b = deck.get(i);

            if (a.getSuit() != b.getSuit()) {
                return false;
            }

            if (a.getRank()+1 != b.getRank()) {
                return false;
            }

        }

        return true;
    }

    //sorts the deck by suit
	public void sortBySuit(){
		deck.sort(new SuitComparator());

	}

	//sorts the deck by rank
	public void sortByRank(){
		deck.sort(new RankComparator());

	}

	//prints the cards in the deck, sorted by rank and sorted by suit
	public void print(){

		int count = 0;

		sortBySuit();

		for(int i = 0; i<deck.size();i++){
			if(count == 0){
				System.out.println("[" + deck.get(i) + ",");
			}
			if(count > 0 && count < deck.size() -1){
				System.out.println(deck.get(i) + ",");
			}
			else{
				System.out.println(deck.get(i) + "]");
			}
		}

		sortByRank();

		for(int i = 0; i<deck.size();i++){
			if(count == 0){
				System.out.println("[" + deck.get(i) + ",");
			}
			if(count > 0 && count < deck.size() -1){
				System.out.println(deck.get(i) + ",");
			}
			else{
				System.out.println(deck.get(i) + "]");
			}
		}
	}

	//prints the deck
	public String toString(){

		String result = "";
		int count = 0;

		for(int i = 0; i<deck.size();i++){

			if(count == 0){
				result += "[" + deck.get(i) + ",";
			}

			if(count > 0 && count < deck.size() -1){
				result += deck.get(i) + ",";
			}

			else{
				result+=deck.get(i) + "]";
			}
		}

		return result;
	}
}
