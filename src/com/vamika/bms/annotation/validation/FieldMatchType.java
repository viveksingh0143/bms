package com.vamika.bms.annotation.validation;

public enum FieldMatchType {

	EQUAL {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			return firstObj == null && secondObj == null || firstObj != null
					&& firstObj.equals(secondObj);
		}
	},
	GREATER_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {

			if (firstObj == null && secondObj == null) {
				return true;
			}

			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) < 0;
		}
	},
	GREATER_EQUAL_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {

			if (firstObj == null && secondObj == null) {
				return true;
			}

			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_EQUAL_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) <= 0;
		}
	};

	public abstract boolean isValid(Object firstObj, Object secondObj);
}
