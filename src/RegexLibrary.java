public abstract class RegexLibrary {
    public static final String SULFURAS = "(?i)(Conjured )?Sulfuras, Hand of Ragnaros";
    public static final String AGEDBRIE = "(?i)(Conjured )?Aged Brie";
    public static final String BACKSTAGEPASSES = "(?i)(Conjured )?Backstage Passes.*";
    public static final String CONJURED = "(?i)Conjured.*";

    public static final String MAXVALSTRING = "(?i)(Int(eger)?)?(_|.|-| |:)?Max(imum)?(_|.|-| |:)?(Int(eger)?)?(_|.|-| |:)?(Value)?";
    public static final String MINVALSTRING = "(?i)(Int(eger)?)?(_|.|-| |:)?Min(imum)?(_|.|-| |:)?(Int(eger)?)?(_|.|-| |:)?(Value)?";
    public static final String ANYITEM = ".*";
}
