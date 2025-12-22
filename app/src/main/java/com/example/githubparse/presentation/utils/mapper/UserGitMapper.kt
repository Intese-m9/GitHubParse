package com.example.githubparse.presentation.utils.mapper

import com.example.githubparse.data.models.getlist.GitHubItemDTO
import com.example.githubparse.data.room.GitHubListEntity
import com.example.githubparse.data.room.GitUserDownloadEntity
import com.example.githubparse.domain.models.GitHubItem

fun GitHubItemDTO.toDomain(): GitHubItem {
    return GitHubItem(
        id = this.id.orZero(), name = this.name.orEmpty(), fullName = this.fullName.orEmpty()
    )
}


fun GitHubItem.toEntity(): GitUserDownloadEntity {
    return GitUserDownloadEntity(
        id = this.id, repo = this.name, data = this.fullName
    )
}

fun GitHubItem.toEntity2(): GitHubListEntity {
    return GitHubListEntity(
        id = this.id, name = this.name, fullName = this.fullName
    )
}

fun GitUserDownloadEntity.toDomain(): GitHubItem {
    return GitHubItem(
        id = this.id, name = this.repo, fullName = this.data
    )
}

fun GitHubListEntity.toDomain(): GitHubItem {
    return GitHubItem(
        id = this.id, name = this.name, fullName = this.fullName
    )
}


// Вспомогательные extension-функции
fun String?.orEmpty() = this ?: ""
fun Int?.orZero() = this ?: 0
