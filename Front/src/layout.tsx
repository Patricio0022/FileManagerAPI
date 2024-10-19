import './styles/main.css';
import { AvatarIcon } from './components/AvatarIcon';
import { InputButton } from './components/InputButton';
import TextSnippetIcon from '@mui/icons-material/TextSnippet';
import PictureAsPdfIcon from '@mui/icons-material/PictureAsPdf';
import JavascriptIcon from '@mui/icons-material/Javascript';
import { useRef, useState } from 'react';

export function App() {
  const inputButtonRef = useRef<any>(null);
  const [selectedIcon, setSelectedIcon] = useState<string>(''); // state to store the selected icon

  const handleIconClick = (icon: string) => {
    console.log(icon, 'icon clicked');
    setSelectedIcon(icon); // set the selected icon
  };

  const handleSubmit = () => {
    if (selectedIcon === 'TextSnippetIcon') {
      inputButtonRef.current.handleSubmitTXT();
    } else if (selectedIcon === 'JavascriptIcon') {
      inputButtonRef.current.handleSubmitJSON();
    } else if (selectedIcon === 'PictureAsPdfIcon') {
      inputButtonRef.current.handleSubmitPDF();
    } else {
      console.log('nobody icon selected!');
    }
  };

  return (
    <div className='container p-20'>
      <div className='flex flex-col items-center justify-center p-none m-none rounded-sm bg-white h-[750px] w-full'>
        <AvatarIcon />
        <div className='p-7 h-[200px] flex flex-col justify-center w-[400px]'>

          <InputButton ref={inputButtonRef} onSubmit={handleSubmit} />

          <div className='flex justify-between w-[200px]'>
            <TextSnippetIcon
              className='mt-5 transition-transform duration-300 ease-in-out transform hover:scale-110'
              fontSize='large'
              sx={{
                color: selectedIcon === 'TextSnippetIcon' ? 'blue' : 'text.primary',
                bgcolor: 'background.paper',
                boxShadow: 1,
                borderRadius: 2,
              }}
              onClick={() => handleIconClick('TextSnippetIcon')}
            />

            <JavascriptIcon
              className='mt-5 transition-transform duration-300 ease-in-out transform hover:scale-110'
              fontSize='large'
              sx={{
                color: selectedIcon === 'JavascriptIcon' ? 'blue' : 'text.primary',
                bgcolor: 'background.paper',
                boxShadow: 1,
                borderRadius: 2,
              }}
              onClick={() => handleIconClick('JavascriptIcon')}
            />

            <PictureAsPdfIcon
              className='mt-5 transition-transform duration-300 ease-in-out transform hover:scale-110'
              fontSize='large'
              sx={{
                color: selectedIcon === 'PictureAsPdfIcon' ? 'blue' : 'text.primary',
                bgcolor: 'background.paper',
                boxShadow: 1,
                borderRadius: 2,
              }}
              onClick={() => handleIconClick('PictureAsPdfIcon')}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
