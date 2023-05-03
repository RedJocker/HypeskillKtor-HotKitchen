package hotkitchen.domain.service

import hotkitchen.data.repository.UserInfoRepository
import hotkitchen.domain.exception.DatabaseException
import hotkitchen.domain.exception.InvalidUpdateException
import hotkitchen.domain.exception.InvalidUserInfoRequest
import hotkitchen.domain.model.User
import hotkitchen.presentation.routing.requestDto.UserInfoUpdateRequest
import hotkitchen.presentation.routing.responseDto.ApiResponse
import hotkitchen.presentation.routing.responseDto.UserInfoResponse

class UserInfoService(private val userInfoRepository: UserInfoRepository) {

    fun getUserInfo(email: String): ApiResponse {

        return UserInfoResponse.fromUserInfo(
            userInfoRepository.getUserInfoByMail(email) ?: throw InvalidUserInfoRequest()
        )
    }

    fun updateUserInfo(email: String, userType: User.Type, userInfoUpdateRequest: UserInfoUpdateRequest): ApiResponse {

        if(email !=  userInfoUpdateRequest.email) {
            throw InvalidUpdateException("Invalid update for user info, email cannot be changed")
        }

        val (name, requestUserType, phone, requestEmail, address) = userInfoUpdateRequest

        val userInfo = if(userType != requestUserType) {
            userInfoRepository.updateOrCreateUserInfo(name, requestUserType.name, phone, requestEmail, address)
        } else {
            userInfoRepository.updateOrCreateUserInfo(name, phone, requestEmail, address)
        } ?: throw DatabaseException()

        return UserInfoResponse.fromUserInfo(userInfo)
    }

    fun deleteUser(email: String) {
        userInfoRepository.deleteUser(email)
    }
}