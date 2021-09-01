package by.akella.benefits.data.datasource.local.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import by.akella.benefits.data.datasource.local.dao.BenefitsDao;
import by.akella.benefits.data.datasource.local.dao.BenefitsDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BenefitsDb_Impl extends BenefitsDb {
  private volatile BenefitsDao _benefitsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `benefits` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `discount` TEXT NOT NULL, `discountType` TEXT NOT NULL, `promo` TEXT NOT NULL, `site` TEXT NOT NULL, `description` TEXT NOT NULL, `icon` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3acd07e4847a375d7da007b6e5f78fb0')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `benefits`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsBenefits = new HashMap<String, TableInfo.Column>(9);
        _columnsBenefits.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("discount", new TableInfo.Column("discount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("discountType", new TableInfo.Column("discountType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("promo", new TableInfo.Column("promo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("site", new TableInfo.Column("site", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBenefits.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBenefits = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBenefits = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBenefits = new TableInfo("benefits", _columnsBenefits, _foreignKeysBenefits, _indicesBenefits);
        final TableInfo _existingBenefits = TableInfo.read(_db, "benefits");
        if (! _infoBenefits.equals(_existingBenefits)) {
          return new RoomOpenHelper.ValidationResult(false, "benefits(by.akella.benefits.data.datasource.local.BenefitEntity).\n"
                  + " Expected:\n" + _infoBenefits + "\n"
                  + " Found:\n" + _existingBenefits);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3acd07e4847a375d7da007b6e5f78fb0", "b1e0ea602d7debcbc631f9f168b4066e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "benefits");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `benefits`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(BenefitsDao.class, BenefitsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public BenefitsDao getBenefitsDao() {
    if (_benefitsDao != null) {
      return _benefitsDao;
    } else {
      synchronized(this) {
        if(_benefitsDao == null) {
          _benefitsDao = new BenefitsDao_Impl(this);
        }
        return _benefitsDao;
      }
    }
  }
}
