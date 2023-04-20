
import smCrypto from 'sm-crypto'

const sm2 = smCrypto.sm2
const sm3 = smCrypto.sm3
const sm4 = smCrypto.sm4
const cipherMode = 1 // 1 - C1C3C2，0 - C1C2C3，默认为1
const publicKey =
	'0468a0666f7242ceb7f5f89f1a4fc599bfed6f22266ad676c7c34fb77561e8b6c8446be55ff45100964b52bfeb38e093ebbedb25bcd1a6867fd64b408fd06cc42d'
const privateKey = 'a872ddb635fc71b6327ad2f3c201c40341968ffb17d595394295469d4d08d1f7'
const key = 'cda9e23b7654a10feb89503c14f2d678'

/**
 * 国密加解密工具类
 */
export default {
	// SM2加密
	doSm2Encrypt(msgString) {
		return sm2.doEncrypt(msgString, publicKey, cipherMode)
	},
	// SM2解密
	doSm2Decrypt(encryptData) {
		return sm2.doDecrypt(encryptData, privateKey, cipherMode)
	},
	// SM2数组加密
	doSm2ArrayEncrypt(msgString) {
		return sm2.doEncrypt(msgString, publicKey, cipherMode)
	},
	// SM2数组解密
	doSm2ArrayDecrypt(encryptData) {
		return sm2.doDecrypt(encryptData, privateKey, cipherMode, { output: 'array' })
	},
	// SM3哈希
	doSm3Hash(msgString) {
		return sm3(msgString)
	},
	// SM4 加密
	doSm4Encrypt(msgString) {
		return sm4.encrypt(msgString, key)
	},
	// SM4 CBC加密
	doSm4CbcEncrypt(msgString) {
		return sm4.encrypt(msgString, key, { mode: 'cbc', iv: 'fedcba98765432100123456789abcdef' })
	},
	// SM4 解密
	doSm4Decrypt(encryptData) {
		return sm4.decrypt(encryptData, key)
	},
	// SM4 CBC解密
	doSm4CbcDecrypt(encryptData) {
		return sm4.decrypt(encryptData, key, { mode: 'cbc', iv: 'fedcba98765432100123456789abcdef' })
	}
}
