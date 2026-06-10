-- =====================
-- 공통 코드 (CommonCode)
-- =====================
INSERT INTO common_code (code_group, code_key, code_name, sort_order, is_active) VALUES
  ('USER_ROLE',        'ADMIN',    '관리자',     1, true),
  ('USER_ROLE',        'USER',     '일반 사용자', 2, true),
  ('PRODUCT_CATEGORY', 'TV',       'TV',         1, true),
  ('PRODUCT_CATEGORY', 'COMPUTER', '컴퓨터',     2, true),
  ('PRODUCT_CATEGORY', 'PHONE',    '휴대폰',     3, true);

-- =====================
-- 상품 100개
-- TV (1~34)
-- =====================
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 43인치', 790000, 'TV', '4K QLED, HDR10+, 스마트TV');
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 50인치', 990000, 'TV', '4K QLED, 게이밍 모드, 돌비 애트모스');
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 55인치', 1200000, 'TV', '4K QLED, HDR지원, Neo Quantum');
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 65인치', 1590000, 'TV', '4K QLED, 대화면, 무한 다크니스');
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 75인치', 2190000, 'TV', '4K QLED, 프리미엄 대화면');
INSERT INTO product (name, price, category, description) VALUES ('삼성 Neo QLED 8K 65인치', 3990000, 'TV', '8K 해상도, Neo Quantum Processor 8K');
INSERT INTO product (name, price, category, description) VALUES ('LG OLED TV 48인치', 1290000, 'TV', 'OLED evo, 완벽한 블랙, α9 프로세서');
INSERT INTO product (name, price, category, description) VALUES ('LG OLED TV 55인치', 1790000, 'TV', 'OLED evo, 돌비비전 IQ, 게임옵티마이저');
INSERT INTO product (name, price, category, description) VALUES ('LG OLED TV 65인치', 2490000, 'TV', 'OLED evo C4, AI 화질/음질');
INSERT INTO product (name, price, category, description) VALUES ('LG OLED TV 77인치', 3490000, 'TV', 'OLED evo 77인치, 갤러리 디자인');
INSERT INTO product (name, price, category, description) VALUES ('LG QNED TV 50인치', 790000, 'TV', 'QNED, 나노셀 디스플레이, 4K');
INSERT INTO product (name, price, category, description) VALUES ('LG QNED TV 65인치', 1190000, 'TV', 'QNED, 미니LED, 로컬디밍');
INSERT INTO product (name, price, category, description) VALUES ('소니 브라비아 43인치', 690000, 'TV', 'X75L, LED 4K HDR, 구글TV');
INSERT INTO product (name, price, category, description) VALUES ('소니 브라비아 55인치', 980000, 'TV', 'X90L, LED 4K, 풀어레이 로컬디밍');
INSERT INTO product (name, price, category, description) VALUES ('소니 브라비아 65인치', 1490000, 'TV', 'X95L, 미니LED, 구글TV, 돌비비전');
INSERT INTO product (name, price, category, description) VALUES ('소니 OLED 55인치', 1890000, 'TV', 'A80L OLED, XR OLED 대비 기술');
INSERT INTO product (name, price, category, description) VALUES ('소니 OLED 65인치', 2890000, 'TV', 'A95L QD-OLED, 퀀텀닷+OLED');
INSERT INTO product (name, price, category, description) VALUES ('필립스 55인치 암비라이트', 990000, 'TV', 'OLED 앰비라이트, 몰입형 조명 효과');
INSERT INTO product (name, price, category, description) VALUES ('TCL QLED 55인치', 599000, 'TV', 'C735 QLED, 144Hz, 게이밍');
INSERT INTO product (name, price, category, description) VALUES ('TCL QLED 65인치', 799000, 'TV', 'C845 Mini LED, 4K 144Hz');
INSERT INTO product (name, price, category, description) VALUES ('하이센스 U8K 55인치', 890000, 'TV', 'Mini-LED ULED, 144Hz, 게이밍TV');
INSERT INTO product (name, price, category, description) VALUES ('하이센스 U8K 65인치', 1190000, 'TV', 'Mini-LED ULED, 4K, 돌비비전');
INSERT INTO product (name, price, category, description) VALUES ('삼성 더 프레임 55인치', 1390000, 'TV', 'QLED 아트모드, 예술작품 디스플레이');
INSERT INTO product (name, price, category, description) VALUES ('삼성 더 프레임 65인치', 1890000, 'TV', 'QLED 아트모드 65인치, 슬림핏 캠');
INSERT INTO product (name, price, category, description) VALUES ('LG 스탠바이미 27인치', 590000, 'TV', '이동식 스크린, 터치, 배터리 내장');
INSERT INTO product (name, price, category, description) VALUES ('삼성 더 세로 43인치', 1290000, 'TV', '세로 모드 TV, 라이프스타일 TV');
INSERT INTO product (name, price, category, description) VALUES ('삼성 더 월 110인치', 15000000, 'TV', 'MicroLED 초대형, 모듈형 디자인');
INSERT INTO product (name, price, category, description) VALUES ('LG 시그니처 OLED R 65인치', 25000000, 'TV', '롤러블 OLED, 세계 최초 롤업 TV');
INSERT INTO product (name, price, category, description) VALUES ('소니 브라비아 85인치', 3290000, 'TV', 'Z9K 미니LED, 8K 해상도');
INSERT INTO product (name, price, category, description) VALUES ('삼성 QLED TV 85인치', 3490000, 'TV', 'Neo QLED 85인치, 대화면 홈시어터');
INSERT INTO product (name, price, category, description) VALUES ('LG OLED evo 83인치', 4990000, 'TV', 'OLED 83인치, 최대 밝기 향상');
INSERT INTO product (name, price, category, description) VALUES ('삼성 게이밍 모니터TV 32인치', 390000, 'TV', 'Odyssey Neo G8, 240Hz, 4K');
INSERT INTO product (name, price, category, description) VALUES ('LG UltraGear OLED 27인치', 790000, 'TV', 'OLED 240Hz 게이밍 모니터');
INSERT INTO product (name, price, category, description) VALUES ('소니 게이밍 TV 55인치', 1390000, 'TV', 'X90L, 120Hz VRR, 4K 게이밍');

-- =====================
-- 컴퓨터 (35~67)
-- =====================
INSERT INTO product (name, price, category, description) VALUES ('삼성 갤럭시북4 Pro 14인치', 1890000, 'COMPUTER', 'Intel Core Ultra 7, 16GB, 512GB SSD');
INSERT INTO product (name, price, category, description) VALUES ('삼성 갤럭시북4 Pro 16인치', 2190000, 'COMPUTER', 'Intel Core Ultra 7, 32GB, 1TB SSD');
INSERT INTO product (name, price, category, description) VALUES ('삼성 갤럭시북4 360 13인치', 1390000, 'COMPUTER', '2-in-1 터치, Intel Core 5, S펜 내장');
INSERT INTO product (name, price, category, description) VALUES ('LG 그램 14인치 2024', 1490000, 'COMPUTER', 'Intel Core Ultra 5, 16GB, 512GB, 980g');
INSERT INTO product (name, price, category, description) VALUES ('LG 그램 16인치 2024', 1890000, 'COMPUTER', 'Intel Core Ultra 7, 32GB, 1TB, 1.19kg');
INSERT INTO product (name, price, category, description) VALUES ('LG 그램 17인치 2024', 2090000, 'COMPUTER', 'Intel Core Ultra 7, 16GB, 1TB, 1.35kg');
INSERT INTO product (name, price, category, description) VALUES ('LG 그램 Pro 16인치', 2490000, 'COMPUTER', 'Intel Core Ultra 7 AI, OLED 디스플레이');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 에어 M2 13인치', 1450000, 'COMPUTER', 'Apple M2, 8GB, 256GB, 팬리스 설계');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 에어 M3 13인치', 1590000, 'COMPUTER', 'Apple M3, 8GB, 256GB, Wi-Fi 6E');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 에어 M3 15인치', 1890000, 'COMPUTER', 'Apple M3, 8GB, 256GB, 15.3인치 Liquid');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 프로 M3 14인치', 2390000, 'COMPUTER', 'Apple M3, 8GB, 512GB, ProMotion 120Hz');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 프로 M3 Pro 14인치', 2990000, 'COMPUTER', 'Apple M3 Pro, 18GB, 512GB');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥북 프로 M3 Max 16인치', 4990000, 'COMPUTER', 'Apple M3 Max, 36GB, 1TB');
INSERT INTO product (name, price, category, description) VALUES ('ASUS ZenBook 14 OLED', 1290000, 'COMPUTER', 'Intel Core Ultra 5, 16GB, 512GB, OLED');
INSERT INTO product (name, price, category, description) VALUES ('ASUS ZenBook Pro 16X OLED', 2490000, 'COMPUTER', 'Intel Core i9, 32GB, 1TB, OLED 4K');
INSERT INTO product (name, price, category, description) VALUES ('ASUS ROG Zephyrus G14', 1990000, 'COMPUTER', 'AMD Ryzen 9, RTX 4070, 16GB, 게이밍');
INSERT INTO product (name, price, category, description) VALUES ('ASUS ROG Strix G16', 2290000, 'COMPUTER', 'Intel Core i9, RTX 4080, 32GB, 게이밍');
INSERT INTO product (name, price, category, description) VALUES ('Dell XPS 13 Plus', 1790000, 'COMPUTER', 'Intel Core Ultra 7, 16GB, 512GB, OLED');
INSERT INTO product (name, price, category, description) VALUES ('Dell XPS 15 OLED', 2590000, 'COMPUTER', 'Intel Core i9, 32GB, 1TB, OLED 3.5K');
INSERT INTO product (name, price, category, description) VALUES ('Dell Inspiron 15 3000', 690000, 'COMPUTER', 'Intel Core i5, 8GB, 256GB, 기본형');
INSERT INTO product (name, price, category, description) VALUES ('HP Spectre x360 14인치', 1890000, 'COMPUTER', '2-in-1 OLED, Intel Core Ultra 7, 32GB');
INSERT INTO product (name, price, category, description) VALUES ('HP EliteBook 840 G11', 2090000, 'COMPUTER', 'Intel Core Ultra 5, 16GB, 512GB, 기업용');
INSERT INTO product (name, price, category, description) VALUES ('레노버 ThinkPad X1 Carbon', 2290000, 'COMPUTER', 'Intel Core Ultra 7, 16GB, 512GB, 1.12kg');
INSERT INTO product (name, price, category, description) VALUES ('레노버 ThinkPad T14s', 1490000, 'COMPUTER', 'AMD Ryzen 7 PRO, 16GB, 512GB');
INSERT INTO product (name, price, category, description) VALUES ('레노버 IdeaPad Slim 5i', 890000, 'COMPUTER', 'Intel Core i5, 16GB, 512GB, 가성비');
INSERT INTO product (name, price, category, description) VALUES ('MSI Prestige 16 Studio', 1990000, 'COMPUTER', 'Intel Core i7, RTX 4060, 16GB, 크리에이터');
INSERT INTO product (name, price, category, description) VALUES ('MSI Creator Z16 HX', 3490000, 'COMPUTER', 'Intel Core i9, RTX 4070, 32GB, 4K OLED');
INSERT INTO product (name, price, category, description) VALUES ('삼성 갤럭시북4 Ultra 16인치', 2990000, 'COMPUTER', 'Intel Core Ultra 9, RTX 4070, 32GB');
INSERT INTO product (name, price, category, description) VALUES ('애플 맥 미니 M2', 890000, 'COMPUTER', 'Apple M2, 8GB, 256GB, 데스크톱');
INSERT INTO product (name, price, category, description) VALUES ('애플 iMac M3 24인치', 2190000, 'COMPUTER', 'Apple M3, 8GB, 256GB, 4.5K 레티나');
INSERT INTO product (name, price, category, description) VALUES ('LG 그램 스타일 14인치 OLED', 1990000, 'COMPUTER', 'Intel Core Ultra 7, 32GB, OLED 2.8K');
INSERT INTO product (name, price, category, description) VALUES ('Razer Blade 15 게이밍', 2890000, 'COMPUTER', 'Intel Core i9, RTX 4080, 32GB, 4K OLED');
INSERT INTO product (name, price, category, description) VALUES ('Microsoft Surface Pro 9', 1690000, 'COMPUTER', 'Intel Core i5, 16GB, 256GB, 2-in-1');

-- =====================
-- 휴대폰 (68~100)
-- =====================
INSERT INTO product (name, price, category, description) VALUES ('아이폰 15 128GB', 1250000, 'PHONE', 'A16 Bionic, Dynamic Island, USB-C');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 15 256GB', 1380000, 'PHONE', 'A16 Bionic, 48MP 메인 카메라');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 15 Pro 128GB', 1550000, 'PHONE', 'A17 Pro, 티타늄, 액션 버튼');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 15 Pro 256GB', 1680000, 'PHONE', 'A17 Pro, 4800만 Pro 카메라 시스템');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 15 Pro Max 256GB', 1870000, 'PHONE', 'A17 Pro, 5배 광학 줌, 120Hz ProMotion');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 16 128GB', 1290000, 'PHONE', 'A18, 카메라 컨트롤, 애플 인텔리전스');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 16 Pro 256GB', 1690000, 'PHONE', 'A18 Pro, 5배 망원, 촬영 버튼');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 16 Pro Max 256GB', 1950000, 'PHONE', 'A18 Pro, 6.9인치, Desert Titanium');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S24 128GB', 1050000, 'PHONE', 'Exynos 2400, 갤럭시 AI, 6.2인치');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S24 256GB', 1150000, 'PHONE', '갤럭시 AI, 서클투서치, 노트하기');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S24+ 256GB', 1350000, 'PHONE', '6.7인치, 45W 급속 충전, ProVisual Engine');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S24 Ultra 256GB', 1690000, 'PHONE', 'S펜 내장, 200MP, 티타늄 프레임');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S24 Ultra 512GB', 1890000, 'PHONE', 'S펜, 200MP 쿼드 카메라, AI 줌');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S25 256GB', 1150000, 'PHONE', 'Snapdragon 8 Elite, AI, 50MP 카메라');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S25+ 256GB', 1390000, 'PHONE', '6.7인치, 차세대 갤럭시 AI');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 S25 Ultra 256GB', 1790000, 'PHONE', 'S펜, 200MP, Snapdragon 8 Elite');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 Z Fold5 256GB', 2190000, 'PHONE', '폴더블, 7.6인치 내부 디스플레이');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 Z Flip5 256GB', 1290000, 'PHONE', '플립형 폴더블, 3.4인치 커버 디스플레이');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 A55 128GB', 590000, 'PHONE', 'Exynos 1480, AMOLED, OIS 카메라');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 A35 128GB', 490000, 'PHONE', '6.6인치 AMOLED, 50MP 카메라, IP67');
INSERT INTO product (name, price, category, description) VALUES ('구글 픽셀 8 128GB', 990000, 'PHONE', 'Tensor G3, 7년 업데이트, AI 기능');
INSERT INTO product (name, price, category, description) VALUES ('구글 픽셀 8 Pro 128GB', 1190000, 'PHONE', 'Tensor G3, 50MP 망원, 온도 센서');
INSERT INTO product (name, price, category, description) VALUES ('구글 픽셀 9 128GB', 1090000, 'PHONE', 'Tensor G4, 제미나이 AI, 50MP 광각');
INSERT INTO product (name, price, category, description) VALUES ('구글 픽셀 9 Pro 256GB', 1390000, 'PHONE', 'Tensor G4, 5배 망원, 빅토리아 티타늄');
INSERT INTO product (name, price, category, description) VALUES ('샤오미 14 Ultra 512GB', 1290000, 'PHONE', 'Snapdragon 8 Gen3, 라이카 카메라');
INSERT INTO product (name, price, category, description) VALUES ('샤오미 14T Pro 512GB', 890000, 'PHONE', 'Dimensity 9300+, 라이카 50MP 카메라');
INSERT INTO product (name, price, category, description) VALUES ('OnePlus 12 256GB', 990000, 'PHONE', 'Snapdragon 8 Gen3, 100W 충전, 하셀블라드');
INSERT INTO product (name, price, category, description) VALUES ('소니 엑스페리아 1 VI', 1490000, 'PHONE', 'Snapdragon 8 Gen3, 4K 21:9 디스플레이');
INSERT INTO product (name, price, category, description) VALUES ('소니 엑스페리아 5 V', 1090000, 'PHONE', '6.1인치 21:9, 120Hz OLED, IP65/68');
INSERT INTO product (name, price, category, description) VALUES ('모토로라 엣지 50 프로', 690000, 'PHONE', 'Snapdragon 7s Gen2, 144Hz POLED');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 Z Fold6 256GB', 2290000, 'PHONE', '폴더블 6세대, 얇은 힌지, AI 기능 강화');
INSERT INTO product (name, price, category, description) VALUES ('갤럭시 Z Flip6 256GB', 1390000, 'PHONE', '플립 6세대, FlexWindow 앱 지원');
INSERT INTO product (name, price, category, description) VALUES ('아이폰 SE 3세대 64GB', 690000, 'PHONE', 'A15 Bionic, 지문인식, 4.7인치');

-- =====================
-- 상품 리뷰/게시글 샘플
-- TV(id=1~3), COMPUTER(id=35~37), PHONE(id=68~69)
-- =====================
INSERT INTO article (product_id, title, content) VALUES (1, '화질이 정말 놀랍습니다', 'QLED 화질이 생각보다 훨씬 좋네요. 색감이 선명하고 빠른 장면에서도 잔상이 없어요.');
INSERT INTO article (product_id, title, content) VALUES (1, '설치가 쉬웠어요', '혼자서 벽걸이 설치까지 완료했습니다. 설명서가 잘 되어 있어서 어렵지 않았어요.');
INSERT INTO article (product_id, title, content) VALUES (2, 'QLED 50인치 리뷰', '방 크기에 딱 맞는 사이즈입니다. 스마트 TV 기능도 편리하게 잘 쓰고 있어요.');
INSERT INTO article (product_id, title, content) VALUES (35, '갤럭시북 가볍고 빠름', '출퇴근 때 들고 다니는데 정말 가볍고 배터리도 오래 갑니다. 만족합니다.');
INSERT INTO article (product_id, title, content) VALUES (35, '개발용으로 완벽', 'Core Ultra 7 성능이 정말 좋습니다. 빌드도 빠르고 발열도 적어요.');
INSERT INTO article (product_id, title, content) VALUES (68, '아이폰 15 사용 한 달 후기', 'USB-C로 바뀐 게 정말 편리합니다. 카메라도 전작보다 훨씬 좋아졌어요.');
INSERT INTO article (product_id, title, content) VALUES (68, '배터리가 좀 아쉬워요', '성능과 카메라는 최고인데 배터리가 하루 버티기 빠듯합니다.');
