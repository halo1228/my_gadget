import _ from 'lodash';
export default {
    // 深拷贝
    copyObj(obj) {
        return _.cloneDeep(obj)
    },
}
