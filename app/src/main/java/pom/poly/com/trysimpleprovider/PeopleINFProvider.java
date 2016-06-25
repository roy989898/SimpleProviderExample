package pom.poly.com.trysimpleprovider;

import de.triplet.simpleprovider.AbstractProvider;
import de.triplet.simpleprovider.Column;
import de.triplet.simpleprovider.Table;

/**
 * Created by User on 25/6/2016.
 */


public class PeopleINFProvider extends AbstractProvider  {
    private static final int PERSON_LOADER = 0;

    protected String getAuthority() {
        return "pom.poly.com.trysimpleprovider";
    }

    @Table
    public class Person {

        @Column(value = Column.FieldType.INTEGER, primaryKey = true)
        public static final String KEY_ID = "_id";

        @Column(Column.FieldType.TEXT)
        public static final String KEY_NAME = "name";

        @Column(Column.FieldType.INTEGER)
        public static final String KEY_YEARDOLD = "yeard_old";

    }


}
