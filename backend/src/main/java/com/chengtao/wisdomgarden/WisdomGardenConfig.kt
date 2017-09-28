interface WisdomGardenConfig {
    companion object {
        const val TITLE: String = "title"
        const val APPLICATION_NAME: String = "智慧园林"
    }

    interface Cookies {
        companion object {
            const val USER_NAME: String = "user_name"
            const val PASSWORD: String = "password"
        }
    }

    interface View {
        interface Index {
            companion object {
                const val VIEW: String = "index"
                const val TITLE: String = WisdomGardenConfig.APPLICATION_NAME
            }
        }

        interface Login {
            companion object {
                const val VIEW: String = "login"
                const val TITLE: String = "登录"
            }
        }
    }
}