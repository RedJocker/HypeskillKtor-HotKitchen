package hotkitchen.presentation.routing

import hotkitchen.domain.exception.*
import hotkitchen.presentation.routing.responseDto.SignInResponse
import hotkitchen.presentation.routing.responseDto.SignInResponse.Status.INVALID_SIGNIN
import hotkitchen.presentation.routing.responseDto.SignupResponse
import hotkitchen.presentation.routing.responseDto.SignupResponse.Status.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

object ErrorRouting {
    fun StatusPages.Configuration.errorRouting() {

        exception<SignupExistingUserException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Forbidden, SignupResponse(DUPLICATED_USER))
        }

        exception<SignupInvalidEmailException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Forbidden, SignupResponse(INVALID_EMAIL))
        }

        exception<SignupInvalidPasswordException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Forbidden, SignupResponse(INVALID_PASS))
        }

        exception<DatabaseException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to cause.localizedMessage))
        }

        exception<SigninAuthorizationFail> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Forbidden, SignInResponse(INVALID_SIGNIN))
        }

        exception<UnauthorizedAccessException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Unauthorized, HttpStatusCode.Unauthorized)
        }

        exception<DeniedAccessException> { cause ->
            logException(cause)
            call.respond(HttpStatusCode.Forbidden, mapOf("status" to "Access denied"))
        }

        exception<InvalidUpdateException>(badRequest)

        exception<InvalidUserInfoRequest>(badRequest)

        exception<InvalidDeleteUserRequestException>(badRequest)

        exception<InvalidMealIdException>(badRequest)

        exception<InvalidCategoryIdException>(badRequest)

        exception<DuplicateMealException>(badRequest)

        exception<DuplicateCategoryException>(badRequest)
    }

    private fun logException(cause: Throwable) {
        println("Exception: ${cause.localizedMessage}")
        //cause.printStackTrace()
    }

    private val badRequest : suspend PipelineContext<Unit, ApplicationCall>.(cause : Throwable)  -> Unit =  { cause ->
        logException(cause)
        call.respond(HttpStatusCode.BadRequest, HttpStatusCode.BadRequest)
    }
}