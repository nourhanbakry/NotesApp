package com.example.notesapp.Model.LocalData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Model.Entity.Note
import kotlin.reflect.KClass

@Database(entities = [Note::class] , version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO

  companion object{
      @Volatile
      var NOTE_DATABASE_INSTANCE : NoteDataBase? = null

      fun getNoteDataBaseInstance(context: Context): NoteDataBase{
          val tempInstance = NOTE_DATABASE_INSTANCE
          if (tempInstance != null){
              return tempInstance
          }
          synchronized(this){
              val instance = Room.databaseBuilder(
                  context.applicationContext,
                  NoteDataBase::class.java,
                  "note_database")
                  .build()
              NOTE_DATABASE_INSTANCE = instance
              return NOTE_DATABASE_INSTANCE!!
          }

      }
  }
}