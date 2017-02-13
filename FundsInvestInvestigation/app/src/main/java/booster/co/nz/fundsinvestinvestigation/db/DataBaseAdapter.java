package booster.co.nz.fundsinvestinvestigation.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import booster.co.nz.fundsinvestinvestigation.model.Question;

/**
 * Created by ximeiliu on 10/02/17.
 */

public class DataBaseAdapter extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "investTypeQuestionniare";

    //table name
    private static final String TABLE_NAME = "QUESTIONNIARE";

    //table commumn names
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_QESTION = "Question";
    private static final String COLUMN_A = "A";
    private static final String COLUMN_B ="B";
    private static final String COLUMN_C = "C";
    private static final String COLUMN_D = "D";
    private static final String COLUMN_E = "E";

    private int rowNumber;
    SQLiteDatabase qDataBase;
    public DataBaseAdapter(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QESTION
                + " TEXT, " + COLUMN_A + " TEXT, "+ COLUMN_B +" TEXT, "
                + COLUMN_C +" TEXT, "+ COLUMN_D +" TEXT, "+ COLUMN_E +" TEXT)";

        db.execSQL(sql);
        this.addQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        //create table again
        onCreate(db);
    }



    public int getRowCount(){

        return  rowNumber;
    }

    public List<Question> getAllQuestions(){
        List<Question> list = new ArrayList<>();
        String query = "SELECT * FROM " +TABLE_NAME;
        qDataBase = this.getReadableDatabase();
        Cursor cursor = qDataBase.rawQuery(query,null);

        rowNumber = cursor.getCount();
        if(cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setOptA(cursor.getString(2));
                q.setOptB(cursor.getString((3)));
                q.setOptC(cursor.getString(4));
                q.setOptD(cursor.getString(5));
                q.setOptE(cursor.getString(6));

                list.add(q);
            } while (cursor.moveToNext());
        }

        //return question list
        return list;
    }

    private void addQuestion(Question q){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QESTION,q.getQuestion());
        contentValues.put(COLUMN_A,q.getOptA());
        contentValues.put(COLUMN_B,q.getOptB());
        contentValues.put(COLUMN_C,q.getOptC());
        contentValues.put(COLUMN_D,q.getOptD());
        contentValues.put(COLUMN_E,q.getOptE());

        //insert row
        qDataBase.insert(TABLE_NAME,null,contentValues);

    }

    private void addQuestions()
    {
        Question q1=new Question("When do you plan to make a signiicant lump sum withdrawal from your portfolio," +
                " for example, for the purchase of a irst home or for retirement needs? (From age 65 at the earliest)",
                "Within 2 years",
                "In 2 to 5 years",
                "In 6 to 10 years",
                "In 11 to 20 years",
                "More than 20 years");

        this.addQuestion(q1);

        Question q2=new Question("Which of the following questions best describes your thoughts about risks and returns? ",
                "I want to minimise my risk and am therefore willing to accept low returns",
                "I am willing to take a moderate amount of risk to generate low to medium returns",
                "I am willing to take a reasonable amount of risk to provide a more medium return",
                "In order to receive higher returns, I am willing to take a relatively high amount of risk",
                "I want to maximise my returns and am willing to accept a high level of risk");
        this.addQuestion(q2);


        Question q3=new Question("Protecting my investment portfolio from a fall in value at any time is" +
                " more important to me than achieving high returns? ",
                "Strongly Agree",
                "Agree",
                "Neither agree or disagree",
                "Disagree",
                "Strongly Disagree");
        this.addQuestion(q3);

        Question q4=new Question("Consider you have an investment balance of at least $20,000 or more. If after a short period of time (less than 1 year) your balance has dropped in value," +
                " which statement relects best how you would feel about this? ",
                " I would be unhappy with any drop in value",
                "I would be OK with a drop in value of no more than 5%",
                "I would be OK with a drop in value of no more than 10% ",
                " I would be OK with a drop in value of up to 15%",
                "I would be OK with a drop in value of up to 20% ");
        this.addQuestion(q4);

        Question q5=new Question("How often would you tend to worry about your investment returns?",
                "Daily",
                "Monthly",
                "Quarterly",
                "Annually",
                "Never or only occasionally over the longer term");
        this.addQuestion(q5);

    }
}
