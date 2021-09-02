package by.akella.benefits.data.datasource.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import by.akella.benefits.data.datasource.local.BenefitEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BenefitsDao_Impl implements BenefitsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BenefitEntity> __insertionAdapterOfBenefitEntity;

  private final EntityDeletionOrUpdateAdapter<BenefitEntity> __deletionAdapterOfBenefitEntity;

  public BenefitsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBenefitEntity = new EntityInsertionAdapter<BenefitEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `benefits` (`id`,`name`,`type`,`discount`,`discountType`,`promo`,`site`,`description`,`icon`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BenefitEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getType());
        }
        if (value.getDiscount() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDiscount());
        }
        if (value.getDiscountType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDiscountType());
        }
        if (value.getPromo() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPromo());
        }
        if (value.getSite() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getSite());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDescription());
        }
        if (value.getIcon() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getIcon());
        }
      }
    };
    this.__deletionAdapterOfBenefitEntity = new EntityDeletionOrUpdateAdapter<BenefitEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `benefits` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BenefitEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final BenefitEntity[] models,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBenefitEntity.insert(models);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final BenefitEntity[] models,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBenefitEntity.handleMultiple(models);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAll(final Continuation<? super List<BenefitEntity>> continuation) {
    final String _sql = "SELECT * FROM benefits";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BenefitEntity>>() {
      @Override
      public List<BenefitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfPromo = CursorUtil.getColumnIndexOrThrow(_cursor, "promo");
          final int _cursorIndexOfSite = CursorUtil.getColumnIndexOrThrow(_cursor, "site");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final List<BenefitEntity> _result = new ArrayList<BenefitEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BenefitEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDiscount;
            if (_cursor.isNull(_cursorIndexOfDiscount)) {
              _tmpDiscount = null;
            } else {
              _tmpDiscount = _cursor.getString(_cursorIndexOfDiscount);
            }
            final String _tmpDiscountType;
            if (_cursor.isNull(_cursorIndexOfDiscountType)) {
              _tmpDiscountType = null;
            } else {
              _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            }
            final String _tmpPromo;
            if (_cursor.isNull(_cursorIndexOfPromo)) {
              _tmpPromo = null;
            } else {
              _tmpPromo = _cursor.getString(_cursorIndexOfPromo);
            }
            final String _tmpSite;
            if (_cursor.isNull(_cursorIndexOfSite)) {
              _tmpSite = null;
            } else {
              _tmpSite = _cursor.getString(_cursorIndexOfSite);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIcon;
            if (_cursor.isNull(_cursorIndexOfIcon)) {
              _tmpIcon = null;
            } else {
              _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            }
            _item = new BenefitEntity(_tmpId,_tmpName,_tmpType,_tmpDiscount,_tmpDiscountType,_tmpPromo,_tmpSite,_tmpDescription,_tmpIcon);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getById(final String id, final Continuation<? super BenefitEntity> continuation) {
    final String _sql = "SELECT * FROM benefits WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BenefitEntity>() {
      @Override
      public BenefitEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfPromo = CursorUtil.getColumnIndexOrThrow(_cursor, "promo");
          final int _cursorIndexOfSite = CursorUtil.getColumnIndexOrThrow(_cursor, "site");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final BenefitEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDiscount;
            if (_cursor.isNull(_cursorIndexOfDiscount)) {
              _tmpDiscount = null;
            } else {
              _tmpDiscount = _cursor.getString(_cursorIndexOfDiscount);
            }
            final String _tmpDiscountType;
            if (_cursor.isNull(_cursorIndexOfDiscountType)) {
              _tmpDiscountType = null;
            } else {
              _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            }
            final String _tmpPromo;
            if (_cursor.isNull(_cursorIndexOfPromo)) {
              _tmpPromo = null;
            } else {
              _tmpPromo = _cursor.getString(_cursorIndexOfPromo);
            }
            final String _tmpSite;
            if (_cursor.isNull(_cursorIndexOfSite)) {
              _tmpSite = null;
            } else {
              _tmpSite = _cursor.getString(_cursorIndexOfSite);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIcon;
            if (_cursor.isNull(_cursorIndexOfIcon)) {
              _tmpIcon = null;
            } else {
              _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            }
            _result = new BenefitEntity(_tmpId,_tmpName,_tmpType,_tmpDiscount,_tmpDiscountType,_tmpPromo,_tmpSite,_tmpDescription,_tmpIcon);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
