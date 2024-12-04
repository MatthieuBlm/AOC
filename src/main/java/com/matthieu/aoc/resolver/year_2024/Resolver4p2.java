package com.matthieu.aoc.resolver.year_2024;

public class Resolver4p2 extends Resolver4p1 {

    @Override
    public boolean solve() throws Exception {

        this.data.forEach((x, y, c) -> count += c == 'A' && isXmas(x, y) ? 1 : 0);

        return true;
    }

    private boolean isXmas(int x, int y) {
        try {
            char tl = this.data.get(x - 1, y - 1);
            char tr = this.data.get(x + 1, y - 1);
            char bl = this.data.get(x - 1, y + 1);
            char br = this.data.get(x + 1, y + 1);
            
            return ((tl == 'M' && br == 'S') || (tl == 'S' && br == 'M')) &&
                    ((tr == 'M' && bl == 'S') || (tr == 'S' && bl == 'M'));
        } catch (Exception e) {}
        
        return false;
    }
}
