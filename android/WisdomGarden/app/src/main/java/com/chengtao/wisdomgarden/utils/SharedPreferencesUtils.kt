@file:Suppress("UNCHECKED_CAST")

package com.chengtao.wisdomgarden.utils

import android.content.Context
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 2017-04-24
 * Time : 00:21
 * Description :
 */

object SharedPreferencesUtils {
  private val threadPoolExecutor = ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS,
      LinkedBlockingQueue())

  /**
   * 保存键值对到指定文件
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @param value 值
   */
  fun putAsynchronous(context: Context, fileKey: String,
      valueKey: String, value: Any) {
    threadPoolExecutor.execute {
      val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
      val editor = sharedPreferences.edit()
      when (value) {
        is Int -> editor.putInt(valueKey, value)
        is String -> editor.putString(valueKey, value)
        is Float -> editor.putFloat(valueKey, value)
        is Boolean -> editor.putBoolean(valueKey, value)
        is Long -> editor.putLong(valueKey, value)
        is Set<*> -> try {
          editor.putStringSet(valueKey, value as Set<String>)
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
      editor.apply()
    }
  }

  /**
   * 保存键值对到指定文件
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @param value 值
   */
  fun put(context: Context, fileKey: String, valueKey: String,
      value: Any) {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    when (value) {
      is Int -> editor.putInt(valueKey, value)
      is String -> editor.putString(valueKey, value)
      is Float -> editor.putFloat(valueKey, value)
      is Boolean -> editor.putBoolean(valueKey, value)
      is Long -> editor.putLong(valueKey, value)
      is Set<*> -> try {
        editor.putStringSet(valueKey, value as Set<String>)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
    editor.apply()
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return int
   */
  fun getInt(context: Context, fileKey: String, valueKey: String): Int {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getInt(valueKey, -1)
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return Float
   */
  fun getFloat(context: Context, fileKey: String, valueKey: String): Float {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getFloat(valueKey, -1f)
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return Long
   */
  fun getLong(context: Context, fileKey: String, valueKey: String): Long {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getLong(valueKey, -1)
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return Float
   */
  fun getString(context: Context, fileKey: String,
      valueKey: String): String {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getString(valueKey, "")
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return Boolean
   */
  fun getBoolean(context: Context, fileKey: String,
      valueKey: String): Boolean {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(valueKey, false)
  }

  /**
   * 从SharedPreferences中获取值
   *
   * @param context 上下文
   * @param fileKey 文件键值
   * @param valueKey 值键值
   * @return Float
   */
  fun getStringSet(context: Context, fileKey: String,
      valueKey: String): Set<String>? {
    val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet(valueKey, null)
  }

  /**
   * 清除
   */
  fun clean(context: Context, fileKey: String) {
    threadPoolExecutor.execute {
      val sharedPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE)
      val editor = sharedPreferences.edit()
      editor.clear()
      editor.apply()
    }
  }
}
