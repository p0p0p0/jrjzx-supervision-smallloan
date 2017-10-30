module.exports = function(grunt) {

    //grunt的初始化配置
    grunt.initConfig({
        //读取配置文件
        pkg: grunt.file.readJSON('package.json'),
    /*    concat: {
            box:{
                //将main.js drag.js bigger.js中的内容合并到dist文件夹main.js中
                files:{
                    'dist/main.js':['main.js','drag.js','bigger.js']
                }
            }
        },*/
        /* 启动时清除build文件夹下的所有JS和CSS */
        clean: {
            files: ['static/build/js/*.min.js','static/build/css/*.min.css'],
        },


        /* 给CSS自动添加浏览器厂商前缀 */
        autoprefixer: {
            options:{
                browsers:["Android 2.3","Android >= 4", "Chrome >= 20", "Firefox >= 24", "Explorer >= 8", "iOS >= 6", "Opera >= 12", "Safari >= 6"]
            },
            multiple_files:{
                expand:true,
                cwd: 'static/css/',
                src : ['*.css','!*.min.css'],
                dest: 'static/css/',
            },
        },

        /* 压缩CSS */
        cssmin: {
            options : {
                compatibility : 'ie8', //设置兼容模式
                noAdvanced : true //取消高级特性
            },
            minify: {
                expand: true,
                cwd: 'static/css/',
                src: ['*.css', '!*.min.css'],
                dest: 'static/build/css/',
                ext: '.min.css',	//保存格式为.min.css
            }
        },
        /* 压缩JS */
        uglify: {
            options: {
                mangle: {except: ['jQuery', '$','require','exports','module']}
            },
            files: {
                expand: true,
                cwd: 'static/js/',
                src: ['**/*.js'],
                dest: 'static/build/js/',
                ext:'.min.js',
            }
        },
        /* 压缩图片大小 */
        imagemin: {
            dist: {
                options: {
                    optimizationLevel: 3 //定义 PNG 图片优化水平
                },
                files: [{
                    expand: true,
                    cwd: 'static/build/img/',
                    src: ['**/*.{png,jpg,jpeg,gif}'], // 优化 img 目录下所有 png/jpg/jpeg 图片
                    dest: 'static/build/img/' // 优化后的图片保存位置，覆盖旧图片，并且不作提示
                }]
            }
        },


        //自动监听任务
        watch:{
            autoprefixer:{
                files:['static/css/**/*.css'],
                tasks:['autoprefixer']
            },
            cssmin:{
                files: ['static/css/**/*.css'],
                tasks: ['cssmin:minify'],
                options: {
                    spawn: false,
                },
            },
            uglify: {
                files: ['static/js/**/*.js'],
                tasks: ['uglify'],
            },
            imagemin:{
                files:['static/**/*.{png,jpg,jpeg,gif}'],
                tasks:['imagemin'],
            },
        }

    });

    //加载任务
    // grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-autoprefixer');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-imagemin');
    grunt.loadNpmTasks('grunt-contrib-watch');

    //注册任务
    grunt.registerTask('default', ['clean', 'autoprefixer', 'cssmin:minify', 'uglify','imagemin', 'watch']);

};