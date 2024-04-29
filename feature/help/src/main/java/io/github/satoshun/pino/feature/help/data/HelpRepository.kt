package io.github.satoshun.pino.feature.help.data

import kotlinx.coroutines.delay
import javax.inject.Inject

internal class HelpRepository @Inject constructor() {
  suspend fun getPopularHelp(): List<HelpItem> {
    delay(300)
    return listOf(
      HelpItem(1, "パスワードを設定する"),
      HelpItem(2, "アカウントを作成する"),
      HelpItem(5, "画像の検索する"),
      HelpItem(3, "アカウントを削除する"),
      HelpItem(4, "アカウントを復元する"),
    )
  }
}

data class HelpItem(
  val id: Long,
  val title: String,
)
