package hotkitchen.domain.service

import hotkitchen.domain.exception.SigninAuthorizationFail
import hotkitchen.domain.exception.SignupInvalidEmailException
import hotkitchen.domain.exception.SignupInvalidPasswordException
import hotkitchen.presentation.routing.requestDto.SignInRequest
import hotkitchen.presentation.routing.requestDto.SignUpRequest
import hotkitchen.presentation.routing.responseDto.ApiResponse
import hotkitchen.presentation.routing.responseDto.TokenResponse
import hotkitchen.data.repository.UserCredentialsRepository
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.util.Validation.isNotValidEmail
import hotkitchen.util.Validation.isNotValidPass

class UserCredentialsService(private val userCredentialsRepository: UserCredentialsRepository, private val tokenService: TokenService) {

    fun signInUser(signInRequest: SignInRequest): ApiResponse {
        val maybeUser = userCredentialsRepository.getUser(signInRequest.email)

        return if (maybeUser?.password != signInRequest.password) {
            throw SigninAuthorizationFail()
        } else {
            TokenResponse(tokenService.produceToken(maybeUser.type, maybeUser.email))
        }
    }

    fun signUpUser(signUpRequest: SignUpRequest): ApiResponse {
        val (email, type, password) = signUpRequest

        if(email.isNotValidEmail()) {
            throw SignupInvalidEmailException()
        } else if(password.isNotValidPass()) {
            throw SignupInvalidPasswordException()
        }

        userCredentialsRepository.addUser(email, type, password)
        return TokenResponse(tokenService.produceToken(type, email))
    }
}