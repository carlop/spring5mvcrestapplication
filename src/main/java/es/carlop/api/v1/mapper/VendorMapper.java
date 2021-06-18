package es.carlop.api.v1.mapper;

import es.carlop.BaseURLs;
import es.carlop.api.v1.model.VendorDTO;
import es.carlop.domain.Vendor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    @AfterMapping
    default void setUrl(Vendor vendor, @MappingTarget VendorDTO vendorDTO) {
        vendorDTO.setVendorUrl(BaseURLs.VENDORS_URL + vendor.getId());
    }

}
