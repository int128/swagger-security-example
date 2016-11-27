package example.api;

import example.model.Error;
import example.model.Pets;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(value = "pets", description = "the pets API")
public interface PetsApi {

    @ApiOperation(value = "Create a pet", notes = "", response = Void.class, tags={ "pets", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Null response", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Void.class) })
    @RequestMapping(value = "/pets",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createPets() {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "List all pets", notes = "", response = Pets.class, tags={ "pets", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An paged array of pets", response = Pets.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Pets.class) })
    @RequestMapping(value = "/pets",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    default ResponseEntity<Pets> listPets(@ApiParam(value = "How many items to return at one time (max 100)") @RequestParam(value = "limit", required = false) Integer limit



) {
        // do some magic!
        return new ResponseEntity<Pets>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific pet", notes = "", response = Pets.class, tags={ "pets", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = Pets.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Pets.class) })
    @RequestMapping(value = "/pets/{petId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    default ResponseEntity<Pets> showPetById(
@ApiParam(value = "The id of the pet to retrieve",required=true ) @PathVariable("petId") String petId


) {
        // do some magic!
        return new ResponseEntity<Pets>(HttpStatus.OK);
    }

}
