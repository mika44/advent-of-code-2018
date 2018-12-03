package fr.game.advent.day02.trial;


public class CountNumberOfId {
	Long numberOfIdContainingExactlyTwoOfAnyLetter;
	Long numberOfIdContainingExactlyThreeOfAnyLetter;
	
	private CountNumberOfId(Long numberOfIdContainingExactlyTwoOfAnyLetter, Long numberOfIdContainingExactlyThreeOfAnyLetter) {
		this.numberOfIdContainingExactlyTwoOfAnyLetter = numberOfIdContainingExactlyTwoOfAnyLetter;
		this.numberOfIdContainingExactlyThreeOfAnyLetter = numberOfIdContainingExactlyThreeOfAnyLetter;
	}
	
	public static CountNumberOfId getInstanceFromContainsTest(Boolean containsExactlyTwoOfAnyLetter, Boolean containsExactlyThreeOfAnyLetter) {
		return new CountNumberOfId(containsExactlyTwoOfAnyLetter ? 1L : 0L, containsExactlyThreeOfAnyLetter ? 1L : 0L);
	}
	
	public static CountNumberOfId getZeroCount() {
		return new CountNumberOfId(0L, 0L);
	}
	
	public static CountNumberOfId sum(CountNumberOfId n, CountNumberOfId m) {
		return new CountNumberOfId(n.numberOfIdContainingExactlyTwoOfAnyLetter + m.numberOfIdContainingExactlyTwoOfAnyLetter, 
							  n.numberOfIdContainingExactlyThreeOfAnyLetter + m.numberOfIdContainingExactlyThreeOfAnyLetter);
	}

}
