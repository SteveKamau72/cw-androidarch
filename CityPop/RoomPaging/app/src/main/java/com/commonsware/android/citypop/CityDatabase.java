/***
 Copyright (c) 2017 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain	a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _Android's Architecture Components_
 https://commonsware.com/AndroidArch
 */

package com.commonsware.android.citypop;

import android.arch.persistence.db.framework.AssetSQLiteOpenHelperFactory;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={City.class}, version=1)
abstract class CityDatabase extends RoomDatabase {
  public abstract City.Store cityStore();

  static final String DB_NAME="un.db";
  private static volatile CityDatabase INSTANCE=null;

  synchronized static CityDatabase get(Context ctxt) {
    if (INSTANCE==null) {
      INSTANCE=create(ctxt);
    }

    return(INSTANCE);
  }

  static CityDatabase create(Context ctxt) {
    RoomDatabase.Builder<CityDatabase> b=
      Room.databaseBuilder(ctxt.getApplicationContext(), CityDatabase.class,
        DB_NAME);

    return(b.openHelperFactory(new AssetSQLiteOpenHelperFactory()).build());
  }
}
