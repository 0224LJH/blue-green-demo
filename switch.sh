#!/bin/bash

echo "=== Blue-Green 배포 스위치 ==="
echo "현재 트래픽 상태:"
curl -s http://localhost:8080/health 2>/dev/null || echo "연결 실패"

echo ""
echo "어느 환경으로 전환하시겠습니까?"
echo "1) Blue (1.0)"
echo "2) Green (2.0)"
read -p "선택 (1 또는 2): " choice

if [ "$choice" = "1" ]; then
    TARGET="blue"
    echo "Blue 환경으로 전환 중..."
elif [ "$choice" = "2" ]; then
    TARGET="green"
    echo "Green 환경으로 전환 중..."
else
    echo "잘못된 선택입니다."
    exit 1
fi

# nginx 설정 변경
sed -i "s/server [a-z]*:8080;/server $TARGET:8080;/" nginx.conf

# nginx 재시작 (설정 반영)
docker-compose restart nginx

echo "✅ $TARGET 환경으로 전환 완료!"
echo ""
echo "결과 확인:"
sleep 2
curl -s http://localhost:8080/health 2>/dev/null || echo "연결 실패"

echo ""
echo "브라우저에서 http://localhost:8080 를 새로고침해보세요!"
EOF