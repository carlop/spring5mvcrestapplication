package es.carlop.controllers.v1;

import es.carlop.BaseURLs;
import es.carlop.api.v1.model.VendorDTO;
import es.carlop.api.v1.model.VendorListDTO;
import es.carlop.services.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "vendor-controller", description = "This is the Vendor controller")
@RestController
@RequestMapping(BaseURLs.VENDORS_URL)
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Operation(summary = "This will get a list of vendors.",
            description = "These are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @Operation(summary = "This will get a vendor specified by id.",
            description = "These are some notes about the API.")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @Operation(summary = "This will create a new vendor.",
            description = "These are some notes about the API.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @Operation(summary = "This will save a modified vendor specified by id.",
            description = "These are some notes about the API.")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO saveVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @Operation(summary = "This will patch a modified vendor specified by id.",
            description = "These are some notes about the API.")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @Operation(summary = "This will delete a vendor specified by id.",
            description = "These are some notes about the API.")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

}
