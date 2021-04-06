//OccurrenceMap is a data structure that stores some numbers, and can return the "highest" or "lowest" occurrence with flexible inserts and removals
//[3, 5, 2, 1, 2, 2, 1] has a maximum occurrence of 3 since the number 2 appears the 3 times


//all operations are log(n)
class OccurrenceMap {
    private Map<Integer, Integer> map = new HashMap();
    private TreeMap<Integer, Integer> occur = new TreeMap();

    public void add(final int x) {
        if (map.containsKey(x)) {
            int prev = map.get(x);
            map.put(x, prev + 1);
            int curr = prev + 1;

            occur.put(prev, occur.get(prev) - 1);
            if (occur.get(prev) == 0) occur.remove(prev);
            occur.put(curr, occur.getOrDefault(curr, 0)+1);
        }
        else {
            map.put(x, 1);
            occur.put(1, occur.getOrDefault(1, 0)+1);
        }
    }
    public void remove(final int x) {

        int prev = map.get(x);
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);

        int curr = prev - 1;
        occur.put(prev, occur.get(prev) - 1);
        if (occur.get(prev) == 0) occur.remove(prev);
        if (curr > 0) {
            occur.put(curr, occur.getOrDefault(curr, 0)+1);
        }
    }
    public int maxOccur() {
        if (occur.isEmpty()) return -1;
        return occur.lastKey();
    }
    public int minOccur() {
        if (occur.isEmpty()) return -1;
        return occur.firstKey();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(map.toString());
        sb.append("\n");
        sb.append(occur.toString());
        return sb.toString();
    }
}
