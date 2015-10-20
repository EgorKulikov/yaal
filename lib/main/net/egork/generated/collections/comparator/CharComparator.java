package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface CharComparator {
    public static final CharComparator DEFAULT = new CharComparator() {
        public int compare(char first, char second) {
            if (first < second) {
                return -1;
            }
            if (first > second) {
                return 1;
            }
            return 0;
        }
    };

	public static final CharComparator REVERSE = new CharComparator() {
		public int compare(char first, char second) {
			if (first < second) {
				return 1;
            }
			if (first > second) {
				return -1;
            }
			return 0;
		}
	};

	public int compare(char first, char second);
}
