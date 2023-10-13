package io.igorcossta.misc;

import io.igorcossta.recipe.RecipeDisabledException;
import io.igorcossta.recipe.RecipeNotFoundException;
import io.igorcossta.recipe.RecipeNotOwnerException;
import io.igorcossta.token.TokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorControllerAdvice {
    public static final String DEFAULT_ERROR_VIEW = "error";
    private static final String ERROR_STATUS = "error_status";
    private static final String ERROR_MESSAGE = "error_message";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView error(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        mav.addObject(ERROR_STATUS, "ERROR");
        mav.addObject(ERROR_MESSAGE, "Sorry, something went wrong. Try again later or contact an admin!");
        return mav;
    }

    @ExceptionHandler({RecipeNotFoundException.class, RecipeDisabledException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView error(HttpServletRequest req, RuntimeException exception) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        mav.addObject(ERROR_STATUS, "RECIPE NOT FOUND");
        mav.addObject(ERROR_MESSAGE, "Sorry, but we couldn't find this recipe!");
        return mav;
    }

    @ExceptionHandler(RecipeNotOwnerException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView error(HttpServletRequest req, RecipeNotOwnerException exception) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        mav.addObject(ERROR_STATUS, "FORBIDDEN");
        mav.addObject(ERROR_MESSAGE, "Sorry, but you are not the owner of this recipe!");
        return mav;
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView processTokenException(TokenException exception) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        mav.addObject(ERROR_STATUS, exception.getExceptionType());
        mav.addObject(ERROR_MESSAGE, exception.getMessage());
        return mav;
    }
}
