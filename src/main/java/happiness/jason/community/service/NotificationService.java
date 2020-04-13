package happiness.jason.community.service;

import happiness.jason.community.dto.NotificationDTO;
import happiness.jason.community.dto.PaginationDTO;
import happiness.jason.community.enums.NotificationStatusEnum;
import happiness.jason.community.enums.NotificationTypeEnum;
import happiness.jason.community.exception.CustomizeErrorCode;
import happiness.jason.community.exception.CustomizeException;
import happiness.jason.community.mapper.NotificationMapper;
import happiness.jason.community.model.Notification;
import happiness.jason.community.model.NotificationExample;
import happiness.jason.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO<NotificationDTO> list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        if (page < 1) {
            page = 1;
        }
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        notificationExample.setOrderByClause("gmt_create desc");
        int totalCount = (int) notificationMapper.countByExample(notificationExample);
        int totalPage = (int) Math.ceil((double) totalCount / size);
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        List<Notification> notifications =
                notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offset, size));
        if (notifications.size() == 0) {
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOs.add(notificationDTO);
        }

        paginationDTO.setData(notificationDTOs);
        paginationDTO.setPagination(totalPage, page);

        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(0);
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(notification.getReceiver() != user.getId()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        // update status from unread to read
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationDTO;
    }
}
