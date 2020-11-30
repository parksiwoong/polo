package popor.com.services.product_services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import popor.com.PoporApplication;
import popor.com.daos.productDaos.ItemDao;
import popor.com.enums.item.AddResult;
import popor.com.utility.IoUtil;
import popor.com.vos.item.AddColorVo;
import popor.com.vos.item.AddSizeVo;
import popor.com.vos.item.AddVo;
import popor.com.vos.item.ItemVo;
import popor.com.vos.users.UserVo;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class ItemService {
    private final DataSource dataSource;
    private final ItemDao itemDao;

    private static final String NAME_REGEX = "^([0-9a-zA-Z가-힣 ]{2,100})$";
    private static final String COLOR_NAME_REGEX = "^([a-zA-Z가-힣 ]{1,100})$";
    private static final String SIZE_NAME_REGEX = "^([0-9A-Z]{1,5})$";

    @Autowired
    public ItemService(DataSource dataSource, ItemDao itemDao) {
        this.dataSource = dataSource;
        this.itemDao = itemDao;
    }

    public long totalCount() {
        try (Connection connection = this.dataSource.getConnection()) {
            return itemDao.totalCount(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<ItemVo> list(int dataPerPage, int page) {
        try (Connection connection = this.dataSource.getConnection()) {
            // SQL limit (offset), (count) 이용
            // 1페이지, 0, 10
            // 2페이지, 10, 10
            // 3페이지, 20, 10
            int offset = (page - 1) * dataPerPage;

            return itemDao.list(connection, offset, dataPerPage);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public AddResult add(UserVo userVo, AddVo addVo) throws
            SQLException {
        if (!addVo.getName().matches(NAME_REGEX) || addVo.getPrice() < 0 ||
                !addVo.getColor().matches(COLOR_NAME_REGEX) ||
                !addVo.getSize().matches(SIZE_NAME_REGEX)) {
            return AddResult.INVALID_INPUT;
        }
        if (userVo == null || userVo.getLevel()>9) {
            return AddResult.NOT_ALLOWED;
        }

        MultipartFile file = addVo.getFile();
        String fileName = null;

        if(file != null) {
            fileName = file.getOriginalFilename();
        }

        long index;

        try (Connection connection = this.dataSource.getConnection()) {
            index = this.itemDao.insertItem(connection, addVo, fileName);
        }

        if(file != null){
            try(InputStream inputStream = file.getInputStream()){
                File file2 = new File(PoporApplication.dataPath + "\\" + index + ".bin");

                try(FileOutputStream outputStream = new FileOutputStream(file2)) {
                    IoUtil.copy(inputStream, outputStream);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return AddResult.SUCCESS;
    }
//---------------------------------------------


}