package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface LongComparator {
    public static final LongComparator DEFAULT = new LongComparator() {
        public int compare(long first, long second) {
            if (first < second) {
                return -1;
            }
            if (first > second) {
                return 1;
            }
            return 0;
        }
    };

	public static final LongComparator REVERSE = new LongComparator() {
		public int compare(long first, long second) {
			if (first < second) {
				return 1;
            }
			if (first > second) {
				return -1;
            }
			return 0;
		}
	};

	public int compare(long first, long second);
}
