package pj.lab11;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
class EvenimentNotFoundException extends RuntimeException
{
	public EvenimentNotFoundException(String message)
	{ super(message); }
}