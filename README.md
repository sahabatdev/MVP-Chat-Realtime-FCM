# MVP-Chat-Realtime-FCM
Aplikasi Android Chat Realtime MVP menggunakan FCM

Cara Membuat Push Notification :
1. Pertama buat project di firebase
2. Jangan lupa downloand google-service.json dan taruh di folder app
3. Lalu lihat file yang terdapat kata-kata firebase, selain itu hanya di perlukan saat membuat chat realtime
4. Untuk tes notifikasi anda bisa request melalui postman seperti berikut jika menggunakan curl 

curl -X POST \
  https://fcm.googleapis.com/fcm/send \
  -H 'Authorization: key=AAAAOqk6gJc:APA91bEZuYZH7gzDz7hbUGSdKvzg-AsXTnC37GdVUzbwaULd6Wikb0GCoe0DDqhsjpnXbZYvXfvkuV0SE80dA0kDeBQd4v3lhZlKlIORe18qIbAr5vu3XSGDg5YsACHZWWFF3C8eXHw_' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7fb99ecb-531d-4359-a5d8-15cbf8aaea23' \
  -d '{
	"notification":
	{
		"title":"Ada pesan masuk",
		"body":"Ada Promo Baru 2"
	},
	"to":"dfkHCvIyM5M:APA91bE8hyM5y52KmGR6-5P1amxSazaUTHMxNDZm1NI4I61XmHhReiw5YpnSZNP8Z9A_E9c4EKTrjRpgRu6DGEAVMf4MeF4zC06WAWBkYBShA-LNzdUQKQEiXwxzg1D5j9gwreg6mgoa"
}

5. Token akan berubah kapanpun setiap saat anda bisa update token ke api anda di class MyFirebaseInstantceIdService

Cara Membuat Realtime Chat :
1. Semua class diperlukan bisa dipelajari sendiri menyesuaikan backend yang dibuat masing masing.

Terimakasih semoga bermanfaat,

@Author : Sahabat Developer (Politeknik Elektronika Negeri Surabaya)
Linked in : https://id.linkedin.com/in/sahabatdev
Youtube : https://www.youtube.com/channel/UCBtwyVyUyS4CmZQdDMQBELw
