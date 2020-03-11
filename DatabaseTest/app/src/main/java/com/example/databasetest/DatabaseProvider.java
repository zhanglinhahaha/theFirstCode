package com.example.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class DatabaseProvider extends ContentProvider {

    private static final String TAG = "DatabaseProvider";
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper databaseHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                Log.d(TAG, "delete: ");
                deleteRows = database.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                Log.d(TAG, "delete: 2");
                String bookId = uri.getPathSegments().get(1);
                deleteRows = database.delete("Book", "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = database.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = database.delete("Category", "id = ?", new String[]{categoryId});
                break;
            default:break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";
            default:break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Uri uri1 = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                Log.d(TAG, "insert: ");
                long newBookId = database.insert("Book", null, values);
                uri1 = Uri.parse("content://" + AUTHORITY + "/book/"
                        + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = database.insert("Category", null, values);
                uri1 = Uri.parse("content://" + AUTHORITY + "/category/"
                        + newCategoryId);
                break;
            default:break;
        }
        return uri1;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        // TODO: Implement this to initialize your content provider on startup.
        databaseHelper = new MyDatabaseHelper(getContext(),
                "BookStore.db", null, 3);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                Log.d(TAG, "query: ");
                cursor = database.query("Book", projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                Log.d(TAG, "query: 2");
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query("Book", projection, "id = ?", new String[]{
                        bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = database.query("Category", projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = database.query("Category", projection, "id = ?", new String[]{
                        categoryId}, null, null, sortOrder);
                break;
                default:break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                Log.d(TAG, "update: ");
                updatedRows = database.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                Log.d(TAG, "update: 2");
                String bookId = uri.getPathSegments().get(1);
                updatedRows = database.update("Book", values, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows = database.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = database.update("Category", values, "id = ?", new String[]{categoryId});
                break;
            default:break;
        }
        return updatedRows;
    }
}
